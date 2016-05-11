package br.com.hs.nfe.fsda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.FsdaImpressos;
import br.com.hs.nfe.queue.XMLEnviados;
import br.com.hs.nfe.vo.NFeVO;

public class EnvFsdaReenviaNFeThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnvFsdaReenviaNFeThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private br.com.hs.nfe.ws.sc.homo.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperHomo = null; 
	private br.com.hs.nfe.ws.rs.homo.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperHomoRS = null; 
	private br.com.hs.nfe.ws.sc.prod.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperProd = null; 
	private br.com.hs.nfe.ws.rs.prod.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperProdRS = null; 
	public EnvFsdaReenviaNFeThread(Estabelecimento config)
	{
		this.nfeRecepcao2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperHomoRS = new br.com.hs.nfe.ws.rs.homo.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperProd = new br.com.hs.nfe.ws.sc.prod.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperProdRS = new br.com.hs.nfe.ws.rs.prod.nferecepcao2.NfeRecepcao2Helper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "EnvFsdaReenviaNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnvFsdaReenviaNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasReenviarFsda(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = FsdaImpressos.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						try
						{
							enviNFeXML = new NFeVO();
							File sefazErpEnviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
							FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
							enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						catch(FileNotFoundException fnfe)
						{
							//:TODO OS arquivo não existem devido a uma parada inesperada do sistema
							//:TODO Volta para o estatus anterior para que os arquivos necessarios neste estatus sejam gerados
							nfe.setXmlAssinado('0');
							nfe.setXmlValido('0');
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
					}
					String retEnviNFe = "";
					try
					{
						if(nfe.getCuf().equalsIgnoreCase("42"))
						{
							if(nfe.getTpAmb() == '1')
							{
								retEnviNFe = nfeRecepcao2HelperProd.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
							if(nfe.getTpAmb() == '2')
							{
								retEnviNFe = nfeRecepcao2HelperHomo.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
						}
						else if(nfe.getCuf().equalsIgnoreCase("43"))
						{
							if(nfe.getTpAmb() == '1')
							{
								retEnviNFe = nfeRecepcao2HelperProdRS.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
							if(nfe.getTpAmb() == '2')
							{
								retEnviNFe = nfeRecepcao2HelperHomoRS.nfeRecepcaoLote2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
							}
						}
					}
					catch(Exception e)
					{
						//:TODO fica tentando ateh conseguir reenviar
//						nfe.setXmlEnviado('4');
//						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
//						updateCommand.execute();
					}
					//TODO: Ler o nRec e armazenar em banco de dados
					Document retEnviNFeDoc = DomHelper.toDocument(retEnviNFe.getBytes());
					NodeList retEnviNFeDocNL = retEnviNFeDoc.getElementsByTagName("nRec");
					NodeList tpAmbNL = retEnviNFeDoc.getElementsByTagName("tpAmb");
					NodeList cStatNL = retEnviNFeDoc.getElementsByTagName("cStat");
					Element nRec = null;
					if(retEnviNFeDocNL.getLength() > 0)
					{
						nRec = (DeferredElementNSImpl)retEnviNFeDocNL.item(0);
					}
					Element tpAmb = null;
					if(tpAmbNL.getLength() > 0)
					{
						tpAmb = (ElementImpl)tpAmbNL.item(0);
					}
					Element cStat = null;
					if(cStatNL.getLength() > 0)
					{
						cStat = (ElementImpl)cStatNL.item(0);
					}
					enviNFeXML.setNRec(nRec.getTextContent());
					enviNFeXML.setTpAmb(tpAmb.getTextContent());

					
					nfe.setCstat(cStat.getTextContent());
					nfe.setNrec(enviNFeXML.getNRec());
					nfe.setXmlEnviado('1');
					
					XMLEnviados.getInstance().add(enviNFeXML);
					FileManager.getInstance().saveFile(config.getEnviNFeXMLEnviados()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", retEnviNFe);
					
					NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
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
				logger.error("Problemas ao interromper a Thread EnviNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
