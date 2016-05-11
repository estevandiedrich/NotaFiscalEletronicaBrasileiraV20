package br.com.hs.nfe.xslt;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.Dpec2Txt;
import br.com.hs.nfe.vo.NFeVO;

public class XsltDpecToTxtThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("XsltDpecToTxtThread");
	private NFeDAO nfeDao = new NFeDAO();
	private Estabelecimento config = null;
	private XsltNFeToTxt xlstNFeToTxt = null;
	public XsltDpecToTxtThread(Estabelecimento config)
	{
		this.xlstNFeToTxt = new XsltNFeToTxt("dpecproc.to.txt.xsl");
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

					List<Nfe> nfeList = nfeDao.procuraDpecRetornoPendente(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = Dpec2Txt.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File retConsDPEC = new File(config.getEnvDpecXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-dpec-aut.xml");
							FileInputStream fis = new FileInputStream(retConsDPEC);
							enviNFeXML.setRetDpecXMLString(IOUtils.toString(fis));
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							IOUtils.closeQuietly(fis);
						}
						byte[] txt = xlstNFeToTxt.transformar(enviNFeXML.getRetDpecXMLString().getBytes());
						if(txt.length>0)
						{
							nfe.setTxtRetornoDpecGerado('1');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							
							FileManager.getInstance().saveFile(config.getConsReciNFeTXT()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-cons-reci-dpec.txt", new String(txt));
						}
						else
						{
							nfe.setTxtRetornoDpecGerado('2');
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
				logger.error("Problemas ao interromper a Thread XsltDpecToTxtThread", e);
			}
			finally
			{
				
			}
		}
	}
}
