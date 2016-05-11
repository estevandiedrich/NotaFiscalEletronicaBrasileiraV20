package br.com.hs.nfe.cancnfe;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
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
import br.com.hs.nfe.queue.XMLAutorizadosCanc;
import br.com.hs.nfe.queue.XMLCanc2Txt;
import br.com.hs.nfe.queue.XMLValidadosPedCanc;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;

public class CancNFeEnviadorThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("CancNFeEnviadorThread");
	private Estabelecimento config = null;
	private NFeDAO nfeDao = new NFeDAO();
	private br.com.hs.nfe.ws.sc.homo.nfecancelamento2.NfeCancelamento2Helper nfeCancelamento2HelperHomo = null;
	private br.com.hs.nfe.ws.rs.homo.nfecancelamento2.NfeCancelamento2Helper nfeCancelamento2HelperHomoRS = null;
	private br.com.hs.nfe.ws.sc.prod.nfecancelamento2.NfeCancelamento2Helper nfeCancelamento2HelperProd = null;
	private br.com.hs.nfe.ws.rs.prod.nfecancelamento2.NfeCancelamento2Helper nfeCancelamento2HelperProdRS = null;
	private br.com.hs.nfe.ws.br.homo.nfecancelamentoscan.NfeCancelamentoScan2Helper nfeCancelamentoScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.nfecancelamentoscan.NfeCancelamento2Helper nfeCancelamentoScan2HelperProd = null;
	public CancNFeEnviadorThread(Estabelecimento config)
	{
		this.nfeCancelamento2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nfecancelamento2.NfeCancelamento2Helper(config);
		this.nfeCancelamento2HelperHomoRS = new br.com.hs.nfe.ws.rs.homo.nfecancelamento2.NfeCancelamento2Helper(config);
		this.nfeCancelamento2HelperProd = new br.com.hs.nfe.ws.sc.prod.nfecancelamento2.NfeCancelamento2Helper(config);
		this.nfeCancelamento2HelperProdRS = new br.com.hs.nfe.ws.rs.prod.nfecancelamento2.NfeCancelamento2Helper(config);
		this.nfeCancelamentoScan2HelperHomo = new br.com.hs.nfe.ws.br.homo.nfecancelamentoscan.NfeCancelamentoScan2Helper(config);
		this.nfeCancelamentoScan2HelperProd = new br.com.hs.nfe.ws.br.prod.nfecancelamentoscan.NfeCancelamento2Helper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "CancNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread CancNFeEnviadorThread_"+config.getCnpj()+"_"+config.getPe() );
		while ( super.isRunning() ) 
		{
			try
			{
					List<Nfe> nfeList = nfeDao.procuraCancelamentoValido(config.getCnpj(),config.getPe());
					for(Nfe nfe:nfeList)
					{
						NFeVO cancNFeXML = XMLValidadosPedCanc.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
						if(cancNFeXML == null)
						{
							cancNFeXML = new NFeVO();
							File sefazErpCancNFe = new File(config.getCancNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+"ped-canc-val.xml");
							FileInputStream fis = new FileInputStream(sefazErpCancNFe);
							cancNFeXML.setChaveAcesso(nfe.getChaveAcesso());
							cancNFeXML.setCancXMLString(IOUtils.toString(fis));
							IOUtils.closeQuietly(fis);
						}
						String retCancNFe = "";
						if(nfe.getCuf().equalsIgnoreCase("42"))
						{
							if(nfe.getTpEmis() == '1' || nfe.getTpEmis() == '4')
							{
								if(nfe.getTpAmb()=='1')
								{
									retCancNFe = nfeCancelamento2HelperProd.nfeCancelamento2(cancNFeXML.getChaveAcesso(),cancNFeXML.getCancXMLString());
								}
								if(nfe.getTpAmb()=='2')
								{
									retCancNFe = nfeCancelamento2HelperHomo.nfeCancelamento2(cancNFeXML.getChaveAcesso(),cancNFeXML.getCancXMLString());
								}
							}
							else if(nfe.getTpEmis() == '3')
							{
								if(nfe.getTpAmb()=='1')
								{
									retCancNFe = nfeCancelamentoScan2HelperProd.nfeCancelamento2(cancNFeXML.getCancXMLString());
								}
								if(nfe.getTpAmb()=='2')
								{
									retCancNFe = nfeCancelamentoScan2HelperHomo.nfeCancelamentoNFScan2(cancNFeXML.getCancXMLString());
								}
							}
						}
						else if(nfe.getCuf().equalsIgnoreCase("43"))
						{
							if(nfe.getTpEmis() == '1')
							{
								if(nfe.getTpAmb()=='1')
								{
									retCancNFe = nfeCancelamento2HelperProdRS.nfeCancelamento2(cancNFeXML.getChaveAcesso(),cancNFeXML.getCancXMLString());
								}
								if(nfe.getTpAmb()=='2')
								{
									retCancNFe = nfeCancelamento2HelperHomoRS.nfeCancelamento2(cancNFeXML.getChaveAcesso(),cancNFeXML.getCancXMLString());
								}
							}
							else if(nfe.getTpEmis() == '3')
							{
								if(nfe.getTpAmb()=='1')
								{
									retCancNFe = nfeCancelamentoScan2HelperProd.nfeCancelamento2(cancNFeXML.getCancXMLString());
								}
								if(nfe.getTpAmb()=='2')
								{
									retCancNFe = nfeCancelamentoScan2HelperHomo.nfeCancelamentoNFScan2(cancNFeXML.getCancXMLString());
								}
							}
						}
						
						//TODO: Ler o nRec e armazenar em banco de dados
						retCancNFe = TrataCaracter.trata(retCancNFe);
						Document retCancNFeDocument = DomHelper.toDocument(retCancNFe.getBytes());
						NodeList infCancNL = retCancNFeDocument.getElementsByTagName("infCanc");
						Element infCanc = null;
						if(infCancNL.getLength() > 0)
						{
							infCanc = (DeferredElementNSImpl)infCancNL.item(0);
						}
						NodeList cStatNL = infCanc.getElementsByTagName("cStat");
						Element cStat = null;
						if(cStatNL.getLength() > 0)
						{
							cStat = (DeferredElementNSImpl)cStatNL.item(0);
						}
						NodeList xMotivoNL = infCanc.getElementsByTagName("xMotivo");
						Element xMotivo = null;
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
						NodeList dhRecbtoNL = infCanc.getElementsByTagName("dhRecbto");
						Element dhRecbto = null;
						if(dhRecbtoNL.getLength() > 0)
						{
							dhRecbto = (DeferredElementNSImpl)dhRecbtoNL.item(0);
						}
						//TODO: converter data e hora e gravar na base
						NodeList nProtNL = infCanc.getElementsByTagName("nProt");
						Element nProt = null;
						if(nProtNL.getLength() > 0)
						{
							nProt = (DeferredElementNSImpl)nProtNL.item(0);
						}
						
						if(cStat.getTextContent().equalsIgnoreCase("101"))
						{
							//NFeVO e = new NFeVO();
							//e.setCancXMLString(cancNFeXML.getCancXMLString());
							//e.setChaveAcesso(cancNFeXML.getChaveAcesso());
							cancNFeXML.setRetCancNFe(retCancNFe);
							
							nfe.setCstat("101");
							nfe.setXmotivo(xMotivo.getTextContent());
							nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							nfe.setNprot(nProt.getTextContent());
							nfe.setXmlCancelado('4');
							
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							
							XMLAutorizadosCanc.getInstance().add(cancNFeXML);
							XMLCanc2Txt.getInstance().add(cancNFeXML);
							FileManager.getInstance().saveFile(config.getCancNFeXMLAutorizados()+File.separatorChar+cancNFeXML.getChaveAcesso()+"-retCancNFe.xml", retCancNFe);
						}
						else
						{
							nfe.setCstat(cStat.getTextContent());			
							nfe.setXmotivo(xMotivo.getTextContent());
							if(dhRecbto != null)
							{
								nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							}
							else
							{
								nfe.setDhRecbto(new Date());
							}
							nfe.setXmlCancelado('5');
							
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
							
							FileManager.getInstance().saveFile(config.getCancNFeXMLDenegados()+File.separatorChar+cancNFeXML.getChaveAcesso()+"-retCancNFe.xml", retCancNFe);
						}
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
				logger.error("Problemas ao interromper a Thread EnviNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}
