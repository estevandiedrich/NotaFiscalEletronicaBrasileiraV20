package br.com.hs.evt.envevento;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.EventoDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.queue.XMLAssinados;
import br.com.hs.nfe.queue.XMLGerados;
import br.com.hs.nfe.signer.AssinarXML;
import br.com.hs.nfe.vo.NFeVO;

public class EnvEventoAssinadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvEventoAssinadorThread");
	private Estabelecimento config = null;
	private AssinarXML assinarXML = null;
	private EventoDAO eventoDao = null;
	public EnvEventoAssinadorThread(Estabelecimento config)
	{
		this.assinarXML = new AssinarXML(config,"//:evento/:infEvento/@Id");
		this.eventoDao = new EventoDAO();
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvEventoAssinadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvEventoAssinadorThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Evento> eventoList = eventoDao.procuraEventosGerados(config.getCnpj(),config.getPe());
				for(Evento evento:eventoList)
				{
					NFeVO envEventoXML = XMLGerados.getInstance().getNFeVOPorChaveAcesso(evento.getId());
					if(envEventoXML == null)
					{
						envEventoXML = new NFeVO();
						File envEvento = new File(config.getEnvEventoXML()+File.separatorChar+evento.getId()+".xml");
						FileInputStream fis = new FileInputStream(envEvento);
						envEventoXML.setChaveAcesso(evento.getId());
						envEventoXML.setEventoXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					Document doc = DomHelper.toDocument(envEventoXML.getEventoXMLString().getBytes());
					
					doc = assinarXML.assinarXMLRemovendoEnvEvento(doc);
					
					envEventoXML.setEventoXMLString(DomHelper.docToXML(doc).toString());
					
					XMLAssinados.getInstance().add(envEventoXML);
					DomHelper.docToXML(doc,new File(config.getEnvEventoXMLAssinados()+File.separatorChar+evento.getId()+".xml"));
					
					evento.setXmlAssinado('1');
					EventoUpdateCommand updateCommand = new EventoUpdateCommand(evento);
					updateCommand.execute();
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
				logger.error("Problemas ao interromper a Thread EnvEventoAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}