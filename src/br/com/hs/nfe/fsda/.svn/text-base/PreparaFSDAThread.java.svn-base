package br.com.hs.nfe.fsda;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.util.InsereXjustDhCont;
import br.com.hs.nfe.vo.NFeVO;

public class PreparaFSDAThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("PreparaFSDAThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private GeradorChaveAcesso geradorChaveAcesso = null;
	public PreparaFSDAThread(Estabelecimento config)
	{
		this.geradorChaveAcesso = new GeradorChaveAcesso();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "PreparaFSDAThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread PreparaFSDAThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{

				List<Nfe> nfeList = nfeDao.procuraFsda(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = new NFeVO();
					File xml = new File(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml");
					FileInputStream fis = new FileInputStream(xml);
					enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
					enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
					IOUtils.closeQuietly(fis);
					
					Document doc = DomHelper.toDocument(enviNFeXML.getNfeXMLString().getBytes());
					String chaveAcesso = geradorChaveAcesso.construirChaveAcesso(doc, "5",nfe.getChaveAcesso(),config);
					InsereXjustDhCont.insereXjustDhCont(doc);
					enviNFeXML.setNfeXMLString(DomHelper.docToXML(doc).toString());
					
					//A nota de origem não sera mais tratada
					nfe.setTpEmis('5');
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();
					
					nfe.setChaveAcesso(chaveAcesso);
					nfe.setXmlValido('0');
					nfe.setXmlAssinado('0');
					nfe.setFsdaPreparado('1');
					if(config.getImprimirDanfeFsdaReenvio() == '0')
					{
						nfe.setDanfeGerado('1');
						nfe.setDanfeImpresso('1');
					}
					updateCommand = new NFeUpdateCommand(nfe);
					updateCommand.execute();

					enviNFeXML.setChaveAcesso(chaveAcesso);
					
					FileManager.getInstance().saveFile(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml",enviNFeXML.getNfeXMLString());
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
				logger.error("Problemas ao interromper a Thread PreparaFSDAThread", e);
			}
			finally
			{
				
			}
		}
	}
}
