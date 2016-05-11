package br.com.hs.evt.envevento;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.dao.EventoDAO;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinados;
import br.com.hs.nfe.queue.XMLValidados;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xml.ValidateXML;

public class EnvEventoValidadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvEventoValidadorThread");
	private Estabelecimento config = null;
	private EventoDAO eventoDao = new EventoDAO();
	private ValidateXML validateXML = null;
	public EnvEventoValidadorThread(Estabelecimento config)
	{
		this.validateXML = new ValidateXML(PropriedadesSistema.getInstance().getSistema().getEnvEventoXSD());
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvEventoValidadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvEventoValidadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Evento> eventoList = eventoDao.procuraEventosAssinados(config.getCnpj(),config.getPe());
				for(Evento evento:eventoList)
				{
					NFeVO envEventoXML = XMLAssinados.getInstance().getNFeVOPorChaveAcesso(evento.getId());
					if(envEventoXML == null)
					{
						envEventoXML = new NFeVO();
						File sefazErpEnviNFe = new File(config.getEnvEventoXMLAssinados()+File.separatorChar+evento.getId()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						envEventoXML.setChaveAcesso(evento.getId());
						envEventoXML.setEventoXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					logger.info("Validando XML "+ envEventoXML.getId() + " com XSD "+PropriedadesSistema.getInstance().getSistema().getEnvEventoXSD());
					String schemaError = validateXML.validateXml(envEventoXML.getEventoXMLString()); 
					if(schemaError.equalsIgnoreCase(""))
					{
						logger.info("XML "+envEventoXML.getChaveAcesso()+" valido ");
						XMLValidados.getInstance().add(envEventoXML);
						FileManager.getInstance().saveFile(config.getEnvEventoXMLValidos()+File.separatorChar+envEventoXML.getChaveAcesso()+".xml", envEventoXML.getEventoXMLString());
						
						evento.setXmlValido('1');
						EventoUpdateCommand updateCommand = new EventoUpdateCommand(evento);
						updateCommand.execute();
						
					}
					else
					{
						logger.info("XML "+envEventoXML.getChaveAcesso()+" Invalido ");
						FileManager.getInstance().saveFile(config.getEnvEventoXMLInvalidos()+File.separatorChar+envEventoXML.getChaveAcesso()+".xml", envEventoXML.getEventoXMLString());
						evento.setXmlValido('2');
						evento.setXmotivo(schemaError);
						EventoUpdateCommand updateCommand = new EventoUpdateCommand(evento);
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
				logger.error("Problemas ao interromper a Thread EnvEventoValidadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
