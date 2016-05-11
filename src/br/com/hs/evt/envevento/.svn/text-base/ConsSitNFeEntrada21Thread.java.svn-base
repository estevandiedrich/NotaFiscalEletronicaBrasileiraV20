package br.com.hs.evt.envevento;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.DeferredElementNSImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.conf.PropriedadesSistema;
import br.com.hs.nfe.conssit.ConsSitNFeDocumentBuilder;
import br.com.hs.nfe.dao.NfeEntradaDAO;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.Evento;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.hb.EventoCreateCommand;
import br.com.hs.nfe.hb.EventoUpdateCommand;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;
import br.com.hs.nfe.queue.XMLEnviados;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.vo.NFeVO;

public class ConsSitNFeEntrada21Thread extends ThreadBase {
	private static final Logger logger = Logger.getLogger("ConsSitNFeEntrada21Thread");
	private br.com.hs.nfe.ws.sc.homo.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperHomo = null;
	private br.com.hs.nfe.ws.rs.homo.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperHomoRS = null;
	private br.com.hs.nfe.ws.sc.prod.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperProd = null;
	private br.com.hs.nfe.ws.rs.prod.nfeconsulta2.NfeConsulta2Helper nfeConsulta2HelperProdRS = null;
	private br.com.hs.nfe.ws.br.homo.nfeconsultascan.NfeConsultaScan2Helper nfeConsultaScan2HelperHomo = null;
	private br.com.hs.nfe.ws.br.prod.nfeconsultascan.NfeConsultaScan2Helper nfeConsultaScan2HelperProd = null;
	private NfeEntradaDAO nfeDao = new NfeEntradaDAO();
	private Estabelecimento config;
	public ConsSitNFeEntrada21Thread(Estabelecimento config)
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
		Thread.currentThread().setName( "ConsSitNFeEntrada21Thread_"+config.getCnpj()+"_"+config.getPe() );
		logger.info("Iniciando thread ConsSitNFeEntrada21Thread_"+config.getCnpj()+"_"+config.getPe());
		while ( super.isRunning() ) 
		{
			try
			{
				List<NfeEntrada> nfeList = nfeDao.consultaEventos();
				for(NfeEntrada nfe:nfeList)
				{
					NFeVO enviNFeXML = XMLEnviados.getInstance().getNFeVOPorChaveAcesso(nfe.getChaveAcesso());
					if(enviNFeXML == null)
					{
						enviNFeXML = new NFeVO();
						File sefazErpEnviNFe = new File(PropriedadesSistema.getInstance().getSistema().getXmlEntradaValido()+File.separatorChar+nfe.getChaveAcesso()+".xml");
						FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
						enviNFeXML.setChaveAcesso(nfe.getChaveAcesso());
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
					}
					NodeList cStatNL = protNFe.getElementsByTagName("cStat");
					Element cStat = null;
					if(cStatNL.getLength() > 0)
					{
						cStat = (DeferredElementNSImpl)cStatNL.item(0);
					}
					
					Evento[] evtArray = this.leEventos(retConsSitNFe);
					for(int i = 0;i < evtArray.length;i++)
					{
						Evento evt = evtArray[i];
						try
						{
							EventoCreateCommand createCommand = new EventoCreateCommand(evt);
							createCommand.execute();
						}
						catch(ConstraintViolationException e)
						{
							logger.error("! ConstraintViolationException !",e);
							EventoUpdateCommand updateCommand = new EventoUpdateCommand(evt);
							updateCommand.execute();
						}
					}
					if(cStat.getTextContent().equalsIgnoreCase("100"))
					{
						FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaAutorizado()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", retConsSitNFe);
						
					}
					else
					{
						FileManager.getInstance().saveFile(PropriedadesSistema.getInstance().getSistema().getXmlEntradaDenegado()+File.separatorChar+enviNFeXML.getChaveAcesso()+".xml", retConsSitNFe);
						
					}
					nfe.setConsultaEventos('2');
					NFeEntradaUpdateCommand nfeUpdateCommand = new NFeEntradaUpdateCommand(nfe);
					nfeUpdateCommand.execute();
				}
			}
			catch(Throwable t)
			{
				logger.error("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( Integer.parseInt(PropriedadesSistema.getInstance().getSistema().getEmailEntradaThreadSleep()) );
			}
			catch ( InterruptedException e ) 
			{
				logger.error("Problemas ao interromper a Thread ConsSitNFeEntrada21Thread", e);
			}
			finally
			{
				
			}
		}
	}
	private void geraXmlVinculacaoPorEvento(Element retEvento,String id)
	{
		Document doc = DomHelper.createEmptyDocument();
		Element nfeRecepcaoEventoResult = doc.createElement("nfeRecepcaoEventoResult");
		nfeRecepcaoEventoResult.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento");
		Element retEnvEvento = doc.createElement("retEnvEvento");
		retEnvEvento.setAttribute("versao", "1.00");
		retEnvEvento.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		Element retEventoImported = (ElementNSImpl)doc.importNode(retEvento, true);
		retEnvEvento.appendChild(retEventoImported);
		nfeRecepcaoEventoResult.appendChild(retEnvEvento);
		doc.appendChild(nfeRecepcaoEventoResult);
		
		File sefazErpEnviNFe = new File(PropriedadesSistema.getInstance().getSistema().getEnvEventoXMLVinculado()+File.separatorChar+id+".xml");
		DomHelper.docToXML(doc, sefazErpEnviNFe);
	}
	private Evento[] leEventos(String retConsSitNFe) throws Exception
	{
		Document retConsSitNFeDocument = DomHelper.toDocument(retConsSitNFe.getBytes());
		NodeList retEventoNL = retConsSitNFeDocument.getElementsByTagName("retEvento");
		NodeList procEventoNFeNL = retConsSitNFeDocument.getElementsByTagName("procEventoNFe");
		Element procEventoNFe = null;
		Evento[] evtArray = new Evento[procEventoNFeNL.getLength()];
		for(int i = 0;i < procEventoNFeNL.getLength();i++)
		{
			
			
			Evento evt = new Evento();
			procEventoNFe = (DeferredElementNSImpl)procEventoNFeNL.item(0);
		
			Element retEvento = null;
			NodeList infEventoNL = procEventoNFe.getElementsByTagName("infEvento");
			Element infEvento = null;
			if(infEventoNL.getLength() > 0)
			{
				infEvento = (DeferredElementNSImpl)infEventoNL.item(0);
			}
			evt.setId(infEvento.getAttribute("Id"));
			this.geraXmlVinculacaoPorEvento((DeferredElementNSImpl)retEventoNL.item(i),evt.getId());
			NodeList cnpjNL = procEventoNFe.getElementsByTagName("CNPJ");
			Element cnpj = null;
			if(cnpjNL.getLength() > 0)
			{
				cnpj = (DeferredElementNSImpl)cnpjNL.item(0);
			}
			evt.setCnpj(cnpj.getTextContent());
			NodeList detEventoNL = procEventoNFe.getElementsByTagName("detEvento");
			Element detEvento = null;
			if(detEventoNL.getLength() > 0)
			{
				detEvento = (DeferredElementNSImpl)detEventoNL.item(0);
			}
			NodeList verEventoNL = procEventoNFe.getElementsByTagName("verEvento");
			Element verEvento = null;
			if(verEventoNL.getLength() > 0)
			{
				verEvento = (DeferredElementNSImpl)verEventoNL.item(0);
			}
			evt.setVerEvento(verEvento.getTextContent());
			NodeList descEventoNL = detEvento.getElementsByTagName("descEvento");
			Element descEvento = null;
			if(descEventoNL.getLength() > 0)
			{
				descEvento = (DeferredElementNSImpl)descEventoNL.item(0);
			}
			evt.setDescEvento(descEvento.getTextContent());
			NodeList xCorrecaoNL = detEvento.getElementsByTagName("xCorrecao");
			Element xCorrecao = null;
			if(xCorrecaoNL.getLength() > 0)
			{
				xCorrecao = (DeferredElementNSImpl)xCorrecaoNL.item(0);
			}
			evt.setXcorrecao(xCorrecao.getTextContent());
			NodeList xCondUsoNL = detEvento.getElementsByTagName("xCondUso");
			Element xCondUso = null;
			if(xCondUsoNL.getLength() > 0)
			{
				xCondUso = (DeferredElementNSImpl)xCondUsoNL.item(0);
			}
			evt.setXcondUso(xCondUso.getTextContent());
			if(retEventoNL.getLength() > 0)
			{
				retEvento = (DeferredElementNSImpl)retEventoNL.item(0);
			}
			NodeList cStatNL = retEvento.getElementsByTagName("cStat");
			Element cStat = null;
			if(cStatNL.getLength() > 0)
			{
				cStat = (DeferredElementNSImpl)cStatNL.item(0);
			}
			evt.setCstat(cStat.getTextContent());
			NodeList xMotivoNL = retEvento.getElementsByTagName("xMotivo");
			Element xMotivo = null;
			if(xMotivoNL.getLength() > 0)
			{
				xMotivo = (DeferredElementNSImpl)xMotivoNL.item(0);
			}
			evt.setXmotivo(xMotivo.getTextContent());
			NodeList dhRegEventoNL = retEvento.getElementsByTagName("dhRegEvento");
			Element dhRegEvento = null;
			if(dhRegEventoNL.getLength() > 0)
			{
				dhRegEvento = (DeferredElementNSImpl)dhRegEventoNL.item(0);
			}
			evt.setDhEvento(NFeSimpleDateFormat.getInstance().parse(dhRegEvento.getTextContent()));
			NodeList nProtNL = retEvento.getElementsByTagName("nProt");
			Element nProt = null;
			if(nProtNL.getLength() > 0)
			{
				nProt = (DeferredElementNSImpl)nProtNL.item(0);
			}
			evt.setNprot(nProt.getTextContent());
			NodeList chNFeNL = retEvento.getElementsByTagName("chNFe");
			Element chNFe = null;
			if(chNFeNL.getLength() > 0)
			{
				chNFe = (DeferredElementNSImpl)chNFeNL.item(0);
			}
			evt.setChNFe(chNFe.getTextContent());
			NodeList cOrgaoNL = retEvento.getElementsByTagName("cOrgao");
			Element cOrgao = null;
			if(cOrgaoNL.getLength() > 0)
			{
				cOrgao = (DeferredElementNSImpl)cOrgaoNL.item(0);
			}
			evt.setCorgao(cOrgao.getTextContent());
			NodeList tpEventoNL = retEvento.getElementsByTagName("tpEvento");
			Element tpEvento = null;
			if(tpEventoNL.getLength() > 0)
			{
				tpEvento = (DeferredElementNSImpl)tpEventoNL.item(0);
			}
			evt.setTpEvento(tpEvento.getTextContent());
			NodeList nSeqEventoNL = retEvento.getElementsByTagName("nSeqEvento");
			Element nSeqEvento = null;
			if(nSeqEventoNL.getLength() > 0)
			{
				nSeqEvento = (DeferredElementNSImpl)nSeqEventoNL.item(0);
			}
			NodeList tpAmbNL = retEvento.getElementsByTagName("tpAmb");
			Element tpAmb = null;
			if(tpAmbNL.getLength() > 0)
			{
				tpAmb = (DeferredElementNSImpl)tpAmbNL.item(0);
			}
			evt.setTpAmb(tpAmb.getTextContent().charAt(0));
			evt.setNseqEvento(nSeqEvento.getTextContent());
			evt.setPe("");
			evt.setCodEstabelecimento(-1);
			evt.setTxtValido('1');
			evt.setXmlAssinado('1');
			evt.setXmlValido('1');
			evt.setXmlEnviado('1');
			evt.setTxtRetornoGerado('0');
			evtArray[i] = evt;
		}
		return evtArray;
	}
}