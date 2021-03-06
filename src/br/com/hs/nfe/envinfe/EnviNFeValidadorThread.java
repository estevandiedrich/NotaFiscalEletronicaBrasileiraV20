package br.com.hs.nfe.envinfe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinados;
import br.com.hs.nfe.queue.XMLValidados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;

public class EnviNFeValidadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnviNFeValidadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private ValidateXML validateXML = null;
	public EnviNFeValidadorThread(Estabelecimento config)
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getEnviNFeXSD());
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnviNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnviNFeValidadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasAssinadas(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLAssinados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						try
						{
							enviNFeXML = new NFeVO();
							File sefazErpEnviNFe = new File(config.getEnviNFeXMLAssinados()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						catch(FileNotFoundException fileNotFoundException)
						{
							logger.error(fileNotFoundException);
							nfe.setXmlValido('2');
							nfe.setXmotivo(fileNotFoundException.getMessage());
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
					}
					logger.info("Validando XML "+ enviNFeXML.getChaveAcesso() + " com XSD "+PropriedadesSistema.getInstance().getSistema().getEnviNFeXSD());
					String schemaError = validateXML.validateXml3(enviNFeXML.getNfeXMLString()); 
					if(schemaError.equalsIgnoreCase(""))
					{
						logger.info("XML "+enviNFeXML.getChaveAcesso()+" valido ");
						XMLValidados.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnviNFeXMLValidos()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", enviNFeXML.getNfeXMLString());
						
						nfe.setXmlValido('1');
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
						
					}
					else
					{
						logger.info("XML "+enviNFeXML.getChaveAcesso()+" Invalido ");
						FileManager.getInstance().saveFile(config.getEnviNFeXMLInvalidos()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", enviNFeXML.getNfeXMLString());
						nfe.setXmlValido('2');
						nfe.setXmotivo(schemaError);
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro n�o capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(config.getEnviNFeThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread EnviNFeValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
