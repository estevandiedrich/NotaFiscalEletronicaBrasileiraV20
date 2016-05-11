package br.com.hs.nfe.conssit;

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
import br.com.hs.nfe.queue.XML2Txt;
import br.com.hs.nfe.queue.XMLAutorizados;
import br.com.hs.nfe.queue.XMLEnviados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;

public class ConsSitNFeThread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("ConsSitNFeThread");
	private Estabelecimento config = null;
	private br.com.hs.nfe.ws.sc.homo.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperHomo = null;
	private br.com.hs.nfe.ws.rs.homo.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperHomoRS = null;
	private br.com.hs.nfe.ws.sc.prod.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperProd = null;
	private br.com.hs.nfe.ws.rs.prod.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperProdRS = null;
	private br.com.hs.nfe.ws.br.homo.nfeconsultascan.NfeConsultaScan2Helper nfeConsultaScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.nfeconsultascan.NfeConsultaScan2Helper nfeConsultaScan2HelperProd = null;
	private NFeDAO nfeDao = new NFeDAO();
	public ConsSitNFeThread(Estabelecimento config)
	{
		this.nfeConsulta2HelperHomo = new br.com.hs.nfe.ws.sc.homo.nfeconsulta2.NfeConsulta2Helper(config);
		this.nfeConsulta2HelperHomoRS = new br.com.hs.nfe.ws.rs.homo.nfeconsulta2.NfeConsulta2Helper(config);
		this.nfeConsulta2HelperProd = new br.com.hs.nfe.ws.sc.prod.nfeconsulta2.NfeConsulta2Helper(config);
		this.nfeConsulta2HelperProdRS = new br.com.hs.nfe.ws.rs.prod.nfeconsulta2.NfeConsulta2Helper(config);
		this.nfeConsultaScan2HelperHomo = new br.com.hs.nfe.ws.br.homo.nfeconsultascan.NfeConsultaScan2Helper(config); 
		this.nfeConsultaScan2HelperProd = new br.com.hs.nfe.ws.br.prod.nfeconsultascan.NfeConsultaScan2Helper(config); 
		this.config = config;
	}
	public void run()
	{
		Thread.currentThread().setName( "ConsSitNFeThread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ConsSitNFeThread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<Nfe> nfeList = nfeDao.procuraNotasDuplicadas(config.getCnpj(),config.getPe());
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
						byte[] ba = IOUtils.toByteArray(fis);
						enviNFeXML.setTpAmb(nfe.getTpAmb().toString());
						enviNFeXML.setNfeXMLString(new String(ba));
						IOUtils.closeQuietly(fis);
					}
					String consSitNFe = ConsSitNFeDocumentBuilder.consSitNFeDocumentBuilder(enviNFeXML,"2.01");
					String retConsSitNFe = "";
					if(nfe.getTpEmis() == '1' || nfe.getTpEmis() == '4' || nfe.getTpEmis() == '5')
					{
						if(nfe.getCuf().equalsIgnoreCase("42"))
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsSitNFe = this.nfeConsulta2HelperProd.nfeConsulta2(consSitNFe,"2.01");
							}
							else
							{
								retConsSitNFe = this.nfeConsulta2HelperHomo.nfeConsulta2(consSitNFe,"2.01");
							}
						}
						else if(nfe.getCuf().equalsIgnoreCase("43"))
						{
							if(nfe.getTpAmb() == '1')
							{
								retConsSitNFe = this.nfeConsulta2HelperProdRS.nfeConsulta2(consSitNFe);
							}
							else
							{
								retConsSitNFe = this.nfeConsulta2HelperHomoRS.nfeConsulta2(consSitNFe);
							}
						}
					}
					else if(nfe.getTpEmis() == '3')
					{
						if(nfe.getTpAmb() == '1')
						{
							retConsSitNFe = this.nfeConsultaScan2HelperProd.nfeConsultaNFScan2(consSitNFe);
						}
						else if(nfe.getTpAmb() == '2')
						{
							retConsSitNFe = this.nfeConsultaScan2HelperHomo.nfeConsultaNFScan2(consSitNFe);
						}
					}
					Document retConsSitNFeDocument = DomHelper.toDocument(retConsSitNFe.getBytes());
					NodeList protNFeNL = retConsSitNFeDocument.getElementsByTagName("protNFe");
					Element protNFe = null;
					if(protNFeNL.getLength() > 0)
					{
						protNFe = (DeferredElementNSImpl)protNFeNL.item(0);
						NodeList cStatNL = protNFe.getElementsByTagName("cStat");
						Element cStat = null;
						if(cStatNL.getLength() > 0)
						{
							cStat = (DeferredElementNSImpl)cStatNL.item(0);
						}
						NodeList xMotivoNL = protNFe.getElementsByTagName("xMotivo");
						Element xMotivo = null;
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
						}
						NodeList dhRecbtoNL = protNFe.getElementsByTagName("dhRecbto");
						Element dhRecbto = null;
						if(dhRecbtoNL.getLength() > 0)
						{
							dhRecbto = (DeferredElementNSImpl)dhRecbtoNL.item(0);
						}
						//TODO: converter data e hora e gravar na base
						NodeList nProtNL = protNFe.getElementsByTagName("nProt");
						Element nProt = null;
						if(nProtNL.getLength() > 0)
						{
							nProt = (DeferredElementNSImpl)nProtNL.item(0);
						}
						if(cStat.getTextContent().equalsIgnoreCase("100"))
						{
							NFeVO e = new NFeVO();
							e.setNfeXMLString(enviNFeXML.getNfeXMLString());
							e.setChaveAcesso(enviNFeXML.getChaveAcesso());
							e.setRetConsReciNFe(retConsSitNFe);
							
							nfe.setCstat("100");
							nfe.setXmotivo(xMotivo.getTextContent());
							nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							nfe.setNprot(nProt.getTextContent());
							
							XMLAutorizados.getInstance().add(e);
							XML2Txt.getInstance().add(e);
							FileManager.getInstance().saveFile(config.getEnviNFeXMLAutorizados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-retConsReciNFe.xml", retConsSitNFe);
							
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
						else
						{
							nfe.setCstat(cStat.getTextContent());
							nfe.setXmotivo(xMotivo.getTextContent());
							nfe.setDhRecbto(NFeSimpleDateFormat.getInstance().parse(dhRecbto.getTextContent()));
							
							FileManager.getInstance().saveFile(config.getEnviNFeXMLDenegados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-retConsReciNFe.xml", retConsSitNFe);
							
							NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
							updateCommand.execute();
						}
					}
					else
					{
						NodeList cStatNL = retConsSitNFeDocument.getElementsByTagName("cStat");
						Element cStat = null;
						if(cStatNL.getLength() > 0)
						{
							cStat = (Element)cStatNL.item(0);
						}
						NodeList xMotivoNL = retConsSitNFeDocument.getElementsByTagName("xMotivo");
						Element xMotivo = null;
						if(xMotivoNL.getLength() > 0)
						{
							xMotivo = (Element)xMotivoNL.item(0);
						}
						nfe.setCstat(cStat.getTextContent());
						nfe.setXmotivo(xMotivo.getTextContent());
						nfe.setDhRecbto(new Date());
						
						FileManager.getInstance().saveFile(config.getEnviNFeXMLDenegados()+File.separatorChar+enviNFeXML.getChaveAcesso()+"-retConsReciNFe.xml", retConsSitNFe);
						
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
				logger.error("Problemas ao interromper a Thread ConsSitNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
}
