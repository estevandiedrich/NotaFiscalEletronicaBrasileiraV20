package br.com.hs.nfe.nfeproc;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.builder.NFeProcDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAutorizados;
import br.com.hs.nfe.queue.XMLProcessados;
import br.com.hs.nfe.queue.XMLNaoPersistidos;
import br.com.hs.nfe.vo.NFeVO;

public class NFeProcThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("NFeProcThread");
	private Estabelecimento config = null;
	private NFeProcDocumentBuilder documentBuilder = null;
	private NFeDAO nfeDao = new NFeDAO();
	public NFeProcThread(Estabelecimento config)
	{
		this.documentBuilder = new NFeProcDocumentBuilder();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "NFeProcThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread NFeProcThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				//for(ConfigVO config:Config.getInstance().configs)
				//{
					List<Nfe> nfeList = nfeDao.procuraNotasAutorizadas(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO enviNFeXML = XMLAutorizados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(enviNFeXML == null)
						{
							enviNFeXML = new NFeVO();
							File sefazErpEnviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							File retEnviNFe = new File(config.getEnviNFeXMLAutorizados()+File.separatorChar+nfe.getChaveAcesso()+"-retConsReciNFe.xml");
							FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
							FileInputStream fis2 = new FileInputStream(retEnviNFe);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
							enviNFeXML.setRetConsReciNFe(IOUtils.toString(fis2));
							IOUtils.closeQuietly(fis);
							IOUtils.closeQuietly(fis2);
						}
						String nfeProc =  documentBuilder.nfeProcDocumentBuilder(enviNFeXML);
						//NFeVO e = new NFeVO();
						//e.setChaveAcesso(enviNFeXML.getChaveAcesso());
						enviNFeXML.setNfeProc(nfeProc);
						
						XMLProcessados.getInstance().add(enviNFeXML);
						XMLNaoPersistidos.getInstance().add(enviNFeXML);
						//XMLDescompactados.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnviNFeXMLProcessados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-nfeProc.xml", nfeProc);
						
						nfe.setXmlProcessado('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
				//}
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
				logger.error("Problemas ao interromper a Thread NFeProcThread", e);
			}
			finally
			{
				
			}
		}
	}
}
