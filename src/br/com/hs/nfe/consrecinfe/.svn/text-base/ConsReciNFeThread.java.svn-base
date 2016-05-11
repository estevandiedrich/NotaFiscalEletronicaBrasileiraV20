package br.com.hs.nfe.consrecinfe;

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
import br.com.hs.nfe.dom.builder.ConsReciNFeDocumentBuilder;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.NFeUpdateCommand;
import br.com.hs.nfe.queue.XML2Txt;
import br.com.hs.nfe.queue.XMLAutorizados;
import br.com.hs.nfe.queue.XMLEnviados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.util.TrataCaracter;
import br.com.hs.nfe.vo.NFeVO;
import br.com.hs.nfe.xpath.XPathUtil;

public class ConsReciNFeThread extends ThreadBase{
	private static final Logger logger = Logger.getLogger("ConsReciNFeThread");
	private Estabelecimento config = null;
	private br.com.hs.nfe.ws.sc.homo.nferetrecepcao2.NfeRetRecepcao2Helper nfeRetRecepcao2HelperHomo = null;
	private br.com.hs.nfe.ws.rs.homo.nferetrecepcao2.NfeRetRecepcao2Helper nfeRetRecepcao2HelperHomoRS = null;
	private br.com.hs.nfe.ws.sc.prod.nferetrecepcao2.NfeRetRecepcao2Helper nfeRetRecepcao2HelperProd = null;
	private br.com.hs.nfe.ws.rs.prod.nferetrecepcao2.NfeRetRecepcao2Helper nfeRetRecepcao2HelperProdRS = null;
	private br.com.hs.nfe.ws.br.homo.nferetrecepcaoscan.NfeRetRecepcaoScan2Helper nfeRetRecepcaoScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.nferetrecepcaoscan.NfeRetRecepcaoScan2Helper nfeRetRecepcaoScan2HelperProd = null;
	private NFeDAO nfeDao = new NFeDAO();
	public ConsReciNFeThread(Estabelecimento config)
	{
		this.nfeRetRecepcao2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nferetrecepcao2.NfeRetRecepcao2Helper(config);
		this.nfeRetRecepcao2HelperHomoRS = new br.com.hs.nfe.ws.rs.homo.nferetrecepcao2.NfeRetRecepcao2Helper(config);
		this.nfeRetRecepcao2HelperProd = new br.com.hs.nfe.ws.sc.prod.nferetrecepcao2.NfeRetRecepcao2Helper(config);
		this.nfeRetRecepcao2HelperProdRS = new br.com.hs.nfe.ws.rs.prod.nferetrecepcao2.NfeRetRecepcao2Helper(config);
		this.nfeRetRecepcaoScan2HelperHomo = new br.com.hs.nfe.ws.br.homo.nferetrecepcaoscan.NfeRetRecepcaoScan2Helper(config);
		this.nfeRetRecepcaoScan2HelperProd = new br.com.hs.nfe.ws.br.prod.nferetrecepcaoscan.NfeRetRecepcaoScan2Helper(config);
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "ConsReciNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ConsReciNFeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{

				List<Nfe> nfeList = nfeDao.procuraNotasEnviadas(config.getCnpj(),config.getPe());
				for(Nfe nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File sefazErpEnviNFe = new File(config.getEnviNFeXMLValidos()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
						enviNFeXML.setNRec(nfe.getNrec());
						enviNFeXML.setTpAmb(XPathUtil.solveXPath(fis, "//:enviNFe/:NFe/:infNFe/:ide/:tpAmb"));
						enviNFeXML.setNfeXMLString(IOUtils.toString(fis));
						IOUtils.closeQuietly(fis);
					}
					String consReciNFe = ConsReciNFeDocumentBuilder.consReciNFeDocumentBuilder(enviNFeXML);
					String retConsReciNFe = "";
					if(nfe.getCuf().equalsIgnoreCase("42"))
					{
						if(nfe.getTpEmis() == '1' || nfe.getTpEmis() == '4' || nfe.getTpEmis() == '5')
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsReciNFe = this.nfeRetRecepcao2HelperProd.nfeRetRecepcao2(enviNFeXML.getChaveAcesso(), consReciNFe);
							}
							else
							{
								retConsReciNFe = this.nfeRetRecepcao2HelperHomo.nfeRetRecepcao2(enviNFeXML.getChaveAcesso(), consReciNFe);
							}
						}
						else if(nfe.getTpEmis() == '3')
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsReciNFe = this.nfeRetRecepcaoScan2HelperProd.nfeRetRecepcaoScan2(consReciNFe);
							}
							else
							{
								retConsReciNFe = this.nfeRetRecepcaoScan2HelperHomo.nfeRetRecepcaoScan2(consReciNFe);
							}
						}
					}
					else if(nfe.getCuf().equalsIgnoreCase("43"))
					{
						if(nfe.getTpEmis() == '1' || nfe.getTpEmis() == '4' || nfe.getTpEmis() == '5')
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsReciNFe = this.nfeRetRecepcao2HelperProdRS.nfeRetRecepcao2(enviNFeXML.getChaveAcesso(), consReciNFe);
							}
							else
							{
								retConsReciNFe = this.nfeRetRecepcao2HelperHomoRS.nfeRetRecepcao2(enviNFeXML.getChaveAcesso(), consReciNFe);
							}
						}
						else if(nfe.getTpEmis() == '3')
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsReciNFe = this.nfeRetRecepcaoScan2HelperProd.nfeRetRecepcaoScan2(consReciNFe);
							}
							else
							{
								retConsReciNFe = this.nfeRetRecepcaoScan2HelperHomo.nfeRetRecepcaoScan2(consReciNFe);
							}
						}
					}
					//NFeVO e = new NFeVO();
					//e.setNfeXMLString(enviNFeXML.getNfeXMLString());
					//e.setChaveAcesso(enviNFeXML.getChaveAcesso());
					retConsReciNFe = TrataCaracter.trata(retConsReciNFe);
					enviNFeXML.setRetConsReciNFe(retConsReciNFe);
					Document retConsReciNFeDocument = DomHelper.toDocument(TrataCaracter.trata(retConsReciNFe).getBytes());
					NodeList protNFeNL = retConsReciNFeDocument.getElementsByTagName("protNFe");
					Element protNFe = null;
					Element cStat = null;
					Element xMotivo = null;
					Element dhRecbto = null;
					Element nProt = null;
					NodeList cStatNL = null;
					NodeList xMotivoNL = null;
					if(protNFeNL.getLength() > 0)
					{
						protNFe = (DeferredElementNSImpl)protNFeNL.item(0);
						cStatNL = protNFe.getElementsByTagName("cStat");
						if(cStatNL.getLength() > 0)
						{
							cStat = (DeferredElementNSImpl)cStatNL.item(0);
						}
						xMotivoNL = protNFe.getElementsByTagName("xMotivo");
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
						NodeList dhRecbtoNL = protNFe.getElementsByTagName("dhRecbto");
						if(dhRecbtoNL.getLength() > 0)
						{
							dhRecbto = (DeferredElementNSImpl)dhRecbtoNL.item(0);
						}
						//TODO: converter data e hora e gravar na base
						NodeList nProtNL = protNFe.getElementsByTagName("nProt");
						if(nProtNL.getLength() > 0)
						{
							nProt = (DeferredElementNSImpl)nProtNL.item(0);
						}
					}
					else
					{
						cStatNL = retConsReciNFeDocument.getElementsByTagName("cStat");
						if(cStatNL.getLength()>0)
						{
							cStat = (DeferredElementNSImpl)cStatNL.item(0);
						}
						xMotivoNL = retConsReciNFeDocument.getElementsByTagName("xMotivo");
						if(xMotivoNL.getLength()>0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
					}
					if(cStat.getTextContent().equalsIgnoreCase("100"))
					{
						
						nfe.setCstat("100");
						nfe.setXmotivo(xMotivo.getTextContent());
						nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
						nfe.setNprot(nProt.getTextContent());
						
						XMLAutorizados.getInstance().add(enviNFeXML);
						XML2Txt.getInstance().add(enviNFeXML);
						FileManager.getInstance().saveFile(config.getEnviNFeXMLAutorizados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-retConsReciNFe.xml", retConsReciNFe);
						
						NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
						updateCommand.execute();
					}
					else
					{
						nfe.setCstat(cStat.getTextContent());			
						nfe.setXmotivo(xMotivo.getTextContent());
						nfe.setDhRecbto(new Date());
						
						FileManager.getInstance().saveFile(config.getEnviNFeXMLDenegados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-retConsReciNFe.xml", retConsReciNFe);
						
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
				logger.error("Problemas ao interromper a Thread EnviNFeAssinadorThread", e);
			}
			finally
			{
				
			}
		}
	}
}

