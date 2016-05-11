package br.com.hs.evt.envevento;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.EventoDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.queue.XML2Txt;
import br.com.hs.nfe.queue.XMLValidados;
import br.com.hs.nfe.vo.NFeVO;

public class EnvEventoEnviadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvEventoEnviadorThread");
	private Estabelecimento config = null;
	private EventoDAO eventoDao = new EventoDAO();
	private br.com.hs.nfe.ws.sc.homo.recepcaoevento.RecepcaoEventoHelper nfeRecepcaoEventoHelperHomo = null;
	private br.com.hs.nfe.ws.sc.prod.recepcaoevento.RecepcaoEventoHelper nfeRecepcaoEventoHelperProd = null;

	public EnvEventoEnviadorThread(Estabelecimento config)
	{
		this.nfeRecepcaoEventoHelperHomo = new br.com.hs.nfe.ws.sc.homo.recepcaoevento.RecepcaoEventoHelper(config);
		this.nfeRecepcaoEventoHelperProd = new br.com.hs.nfe.ws.sc.prod.recepcaoevento.RecepcaoEventoHelper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvEventoEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvEventoEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Evento> eventoList = eventoDao.procuraEventosValidos(config.getCnpj(),config.getPe());
				for(Evento evento:eventoList)
				{
					NFeVO envEventoXML = XMLValidados.getInstance().getNFeVOPorChaveAcesso(evento.getId());
					if(envEventoXML == null)
					{
						envEventoXML = new NFeVO();
						File envEvento = new File(config.getEnvEventoXMLValidos()+File.separatorChar+evento.getId()+".xml");
						FileInputStream fis = new FileInputStream(envEvento);
						envEventoXML.setChaveAcesso(evento.getId());
						envEventoXML.setEventoXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					String retEnvEvento = "";
					try
					{
						if(evento.getCorgao().equalsIgnoreCase("42"))
						{
							if(evento.getTpAmb() == '1')
							{
								retEnvEvento = nfeRecepcaoEventoHelperProd.nfeRecepcaoEvento(envEventoXML.getChaveAcesso(),envEventoXML.getEventoXMLString());
							}
							if(evento.getTpAmb() == '2')
							{
								retEnvEvento = nfeRecepcaoEventoHelperHomo.nfeRecepcaoEvento(envEventoXML.getChaveAcesso(),envEventoXML.getEventoXMLString());
							}
						}
						else if(evento.getCorgao().equalsIgnoreCase("43"))
						{
							if(evento.getTpAmb() == '1')
							{
								//retEnvEvento = nfeRecepcao2HelperProdRS.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
							if(evento.getTpAmb() == '2')
							{
								//retEnvEvento = nfeRecepcao2HelperHomoRS.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
						}
					}
					catch(Exception e)
					{
						logger.error("Erro ao enviar evento",e);
						evento.setXmlEnviado('2');
						evento.setXmotivo("Falha na conexão.");
						EventoUpdateCommand updateCommand = new EventoUpdateCommand(evento);
						updateCommand.execute();
					}
					if(!retEnvEvento.equalsIgnoreCase(""))
					{
						Document retEnvEventoDoc = DomHelper.toDocument(retEnvEvento.getBytes());
						NodeList retEventoDocNL = retEnvEventoDoc.getElementsByTagName("retEvento");
						Element retEvento = null;
						if(retEventoDocNL.getLength() > 0)
						{
							retEvento = (DeferredElementNSImpl)retEventoDocNL.item(0);
						}
						NodeList cStatNL = retEvento.getElementsByTagName("cStat");
						NodeList xMotivoNL = retEvento.getElementsByTagName("xMotivo");
						Element cStat = null;
						Element xMotivo = null;
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
						if(cStatNL.getLength() > 0)
						{
							cStat = (ElementImpl)cStatNL.item(0);
						}
						
						evento.setCstat(cStat.getTextContent());
						evento.setXmotivo(xMotivo.getTextContent());
						evento.setXmlEnviado('1');
						
						if(cStat.getTextContent().equalsIgnoreCase("135"))
						{
							Element nProt = null;
							NodeList nProtNL = retEvento.getElementsByTagName("nProt");
							if(nProtNL.getLength() > 0)
							{
								nProt = (DeferredElementNSImpl)nProtNL.item(0);
							}
							evento.setNprot(nProt.getTextContent());
							envEventoXML.setRetEnvEvento(retEnvEvento);
							XML2Txt.getInstance().add(envEventoXML);
							
							FileManager.getInstance().saveFile(config.getEnvEventoXMLVinculado()+File.separatorChar+envEventoXML.getChaveAcesso()+".xml", retEnvEvento);
						}
						else
						{
							FileManager.getInstance().saveFile(config.getEnvEventoXMLRejeitado()+File.separatorChar+envEventoXML.getChaveAcesso()+".xml", retEnvEvento);
						}
						
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
				logger.error("Problemas ao interromper a Thread EnvEventoEnviadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
