package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XML2Txt;
import br.com.hs.nfe.vo.NFeVO;

public class XsltNFeToTxtThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("XsltNFeToTxtThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToTxt = null;
	private SimpleDateFormat sdf = null;
	private SimpleDateFormat stf = null;
	public XsltNFeToTxtThread(Estabelecimento config)
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("nfeproc.to.txt.xsl");
		this.sdf = new SimpleDateFormat("yyyyMMdd");
		this.stf = new SimpleDateFormat("HHmmss");
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "XlstNFeToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread XlstNFeToTxtThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraXMLRetornoPendente(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XML2Txt.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File retConsReciNFe = new File(config.getEnviNFeXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-retConsReciNFe.xml");
						FileInputStream fis = new FileInputStream(retConsReciNFe);
						enviNFeXML.setRetConsReciNFe(IOUtils.toString(fis));
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						IOUtils.closeQuietly(fis);
					}
					byte[] txt = xlstNFeToTxt.transformar(enviNFeXML.getRetConsReciNFe().getBytes());
					if(txt.length>0)
					{
						FileManager.getInstance().saveFile(config.getConsReciNFeTXT()+File.separatorChar+enviNFeXML.getChaveAcesso()+"_"+sdf.format(nfe.getDhRecbto())+"_"+stf.format(nfe.getDhRecbto())+"_envi.txt", new String(txt));
						
						nfe.setTxtRetornoGerado('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
					else
					{
						nfe.setTxtRetornoGerado('2');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
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
				Thread.sleep( Integer.parseInt(config.getEnviNFeThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread XsltNFeToTxtThread", e);
			}
			finally
			{
				
			}
		}
	}
}
