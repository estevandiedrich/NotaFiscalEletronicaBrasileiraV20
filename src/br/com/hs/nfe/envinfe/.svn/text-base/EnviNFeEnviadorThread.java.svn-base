package br.com.hs.nfe.envinfe;

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
import br.com.hs.nfe.dao.NFeDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.dom.builder.ConsStatServDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XMLEnviados;
import br.com.hs.nfe.queue.XMLValidados;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;

public class EnviNFeEnviadorThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("EnviNFeEnviadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private GeradorChaveAcesso geradorChaveAcesso = null;
	private br.com.hs.nfe.ws.sc.homo.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperHomo = null;
	private br.com.hs.nfe.ws.rs.homo.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperHomoRS = null;
	private br.com.hs.nfe.ws.sc.prod.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperProd = null;
	private br.com.hs.nfe.ws.rs.prod.nferecepcao2.NfeRecepcao2Helper nfeRecepcao2HelperProdRS = null;
	private br.com.hs.nfe.ws.br.homo.nferecepcaoscan.NfeRecepcaoScan2Helper nfeRecepcaoScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.nferecepcaoscan.NfeRecepcaoScan2Helper nfeRecepcaoScan2HelperProd = null;
	private br.com.hs.nfe.ws.br.homo.nfestatusservicoscan.NfeStatusServicoScan2Helper nfeStatusServicoScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.statusservicoscan.NfeStatusServicoScan2Helper nfeStatusServicoScan2HelperProd = null;

	public EnviNFeEnviadorThread(Estabelecimento config)
	{
		this.nfeRecepcao2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperHomoRS = new br.com.hs.nfe.ws.rs.homo.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperProd = new br.com.hs.nfe.ws.sc.prod.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcao2HelperProdRS = new br.com.hs.nfe.ws.rs.prod.nferecepcao2.NfeRecepcao2Helper(config);
		this.nfeRecepcaoScan2HelperHomo = new br.com.hs.nfe.ws.br.homo.nferecepcaoscan.NfeRecepcaoScan2Helper(config);
		this.nfeRecepcaoScan2HelperProd = new br.com.hs.nfe.ws.br.prod.nferecepcaoscan.NfeRecepcaoScan2Helper(config);
		this.nfeStatusServicoScan2HelperHomo = new br.com.hs.nfe.ws.br.homo.nfestatusservicoscan.NfeStatusServicoScan2Helper(config);
		this.nfeStatusServicoScan2HelperProd = new br.com.hs.nfe.ws.br.prod.statusservicoscan.NfeStatusServicoScan2Helper(config);
		this.geradorChaveAcesso = new GeradorChaveAcesso();
		this.config = config;
	}
	private void criaNotaScan(Nfe nfe,NFeVO enviNFeXML) throws Exception
	{
		String retConsStatServ = "";
		try
		{
			if(nfe.getTpAmb() == '1')
			{
				retConsStatServ = nfeStatusServicoScan2HelperProd.nfeStatusServicoNFScan2(ConsStatServDocumentBuilder.consStatServDocumentBuilder(nfe));
			}
			if(nfe.getTpAmb() == '2')
			{
				retConsStatServ = nfeStatusServicoScan2HelperHomo.nfeStatusServicoNFScan2(ConsStatServDocumentBuilder.consStatServDocumentBuilder(nfe));
			}
		}
		catch(Exception e)
		{
			if(config.getAtivarDpecAutomatico() == '1')
			{
				nfe.setXmlEnviado('2');
				nfe.setXmotivo("DPEC");
				NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
				updateCommand.execute();
			}
			else
			{
				
			}
		}
		if(!retConsStatServ.equalsIgnoreCase(""))
		{
			Document retConsStatServDoc = DomHelper.toDocument(retConsStatServ.getBytes());
			NodeList cStatNL = retConsStatServDoc.getElementsByTagName("cStat");
			Element cStat = null;
			if(cStatNL.getLength() > 0)
			{
				cStat = (ElementImpl)cStatNL.item(0);
			}
			if(cStat.getTextContent().equalsIgnoreCase("107"))
			{
				File sefazErpEnviNFe = new File(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml");
				FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
				enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
				enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
				IOUtils.closeQuietly(fis);
				
				Document doc = DomHelper.toDocument(enviNFeXML.getNfeXMLString().getBytes());
				String chaveAcesso = geradorChaveAcesso.construirChaveAcessoScan(doc, "3",nfe.getChaveAcesso(),config);
				enviNFeXML.setNfeXMLString(DomHelper.docToXML(doc).toString());
					
				nfe.setXmlEnviado('1');
				
				NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
				updateCommand.execute();
				
				nfe.setChaveAcesso(chaveAcesso);
				nfe.setTpEmis('3');
				nfe.setXmlValido('0');
				nfe.setXmlAssinado('0');
				nfe.setXmlEnviado('0');

				FileManager.getInstance().saveFile(config.getEnviNFeXML()+File.separatorChar+nfe.getChaveAcesso()+".xml",enviNFeXML.getNfeXMLString());
				
				updateCommand = new NFeUpdateCommand(nfe);
				updateCommand.execute();
			}
		}
	}
	public void run()
	{
		Thread.currentThread().setName( "EnviNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread EnviNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasValidas(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLValidados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File sefazErpEnviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					String retEnviNFe = "";
					try
					{
						if(nfe.getCuf().equalsIgnoreCase("42"))
						{
							if(nfe.getTpEmis() == '1')
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
							else if(nfe.getTpEmis() == '3')
							{
								if(nfe.getTpAmb() == '1')
								{
									retEnviNFe = nfeRecepcaoScan2HelperProd.nfeRecepcaoLoteScan2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
								}
								if(nfe.getTpAmb() == '2')
								{
									retEnviNFe = nfeRecepcaoScan2HelperHomo.nfeRecepcaoLoteScan2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
								}
							}
						}
						else if(nfe.getCuf().equalsIgnoreCase("43"))
						{
							if(nfe.getTpEmis() == '1')
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
							else if(nfe.getTpEmis() == 3)
							{
								if(nfe.getTpAmb() == '1')
								{
									retEnviNFe = nfeRecepcaoScan2HelperProd.nfeRecepcaoLoteScan2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
								}
								if(nfe.getTpAmb() == '2')
								{
									retEnviNFe = nfeRecepcaoScan2HelperHomo.nfeRecepcaoLoteScan2(enviNFeXML.getChaveAcesso(),enviNFeXML.getNfeXMLString());
								}
							}
						}
					}
					catch(Exception e)
					{
						logger.error("Erro ao enviar nota",e);
						if(nfe.getTpEmis() == '1')
						{
							this.criaNotaScan(nfe,enviNFeXML);
						}
						else
						{
							if(nfe.getTpEmis() == '3')
							{
								if(config.getAtivarDpecAutomatico() == '1')
								{
									nfe.setXmlEnviado('2');
									nfe.setXmotivo("DPEC");
									NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
									updateCommand.execute();
								}
								else
								{
									
								}
							}
						}
					}
					//TODO: Ler o nRec e armazenar em banco de dados
					if(!retEnviNFe.equalsIgnoreCase(""))
					{
						retEnviNFe = TrataCaracter.trata(retEnviNFe);
						Document retEnviNFeDoc = DomHelper.toDocument(retEnviNFe.getBytes());
						NodeList retEnviNFeDocNL = retEnviNFeDoc.getElementsByTagName("nRec");
						NodeList tpAmbNL = retEnviNFeDoc.getElementsByTagName("tpAmb");
						NodeList cStatNL = retEnviNFeDoc.getElementsByTagName("cStat");
						Element nRec = null;
						Element tpAmb = null;
						Element cStat = null;
						if(retEnviNFeDocNL.getLength() > 0)
						{
							nRec = (DeferredElementNSImpl)retEnviNFeDocNL.item(0);
						}
						if(tpAmbNL.getLength() > 0)
						{
							tpAmb = (ElementImpl)tpAmbNL.item(0);
						}
						if(cStatNL.getLength() > 0)
						{
							cStat = (ElementImpl)cStatNL.item(0);
						}
						enviNFeXML.setNRec(nRec.getTextContent());
						enviNFeXML.setTpAmb(tpAmb.getTextContent());
	
						if(cStat.getTextContent().equalsIgnoreCase("108") || cStat.getTextContent().equalsIgnoreCase("109"))
						{
							try
							{
								this.criaNotaScan(nfe,enviNFeXML);
							}
							catch(Exception e)
							{
								logger.error("Erro ao gerar nota SCAN",e);
								nfe.setXmlEnviado('2');
								NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
								updateCommand.execute();
							}
						}
						else
						{
						
							nfe.setCstat(cStat.getTextContent());
							nfe.setNrec(enviNFeXML.getNRec());
							nfe.setXmlEnviado('1');
							
							XMLEnviados.getInstance().add(enviNFeXML);
							
							FileManager.getInstance().saveFile(config.getEnviNFeXMLEnviados()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", retEnviNFe);
							
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
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
				logger.error("Problemas ao interromper a Thread EnviNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
