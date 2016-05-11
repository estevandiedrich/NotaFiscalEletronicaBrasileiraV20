package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.NfeEntradaDAO;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;
import br.com.hs.nfe.queue.DanfeEntradaGerados;
import br.com.hs.nfe.queue.TxtRetornoNFeEntradaGerado;
import br.com.hs.nfe.vo.NFeVO;

public class XsltNFeEntradaToTxtThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("XsltNFeEntradaToTxtThread");
	private NfeEntradaDAO nfeDao = new NfeEntradaDAO();
	private XsltNFeToTxt xlstNFeToTxt = null;	
	public XsltNFeEntradaToTxtThread()
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("nfeentrada.to.txt.xsl");
	}
	public void run()
	{
		Thread.currentThread().setName( "XsltNFeEntradaToTxtThread " );
		logger.info("Iniciando thread XsltNFeEntradaToTxtThread " );
		while ( super.isRunning() ) 
		{
			try
			{
				List<NfeEntrada> nfeList = nfeDao.procuraTxtEntradaPendente();
				for(NfeEntrada nfe:nfeList)
				{
					NFeVO enviNFeXML = DanfeEntradaGerados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File nfeProc = new File(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(nfeProc);
						enviNFeXML.setNfeProc(IOUtils.toString(fis));
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						IOUtils.closeQuietly(fis);
					}
					byte[] txt = xlstNFeToTxt.transformar(enviNFeXML.getNfeProc().getBytes());
					if(txt.length>0)
					{
						FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getTxtRetorno()+File.separatorChar+enviNFeXML.getChaveAcesso()+".txt", new String(txt));
						
						TxtRetornoNFeEntradaGerado.getInstance().add(enviNFeXML);
						
						nfe.setTxtGerado('1');
						NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
						updateCommand.execute();
					}
					else
					{
						nfe.setTxtGerado('2');
						NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
						updateCommand.execute();
					}
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailEntradaThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread XsltNFeEntradaToTxtThread", e);
			}
			finally
			{
				
			}
		}
	}
}