package br.com.hs.nfe.dom.builder;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class DpecProcDocumentBuilder {
	private static final Logger logger = Logger.getLogger("DpecProcDocumentBuilder");
	public String dpecProcDocumentBuilder(NFeVO e)
	{
		Document nfeProcDocument = DomHelper.createEmptyDocument();
		Element nfeProc = (ElementImpl)nfeProcDocument.createElement("nfeProc");
		nfeProc.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		nfeProc.setAttribute("versao", "2.00");
		nfeProcDocument.appendChild(nfeProc);
		Document enviNFeDocument = null;
		Document retDpecDocument = null;
		try {
			enviNFeDocument = DomHelper.toDocument(e.getNfeXMLString().getBytes());
			retDpecDocument = DomHelper.toDocument(e.getRetDpecXMLString().getBytes());
			NodeList nfeNL = enviNFeDocument.getElementsByTagName("NFe");
			Element nfe = null;
			if(nfeNL.getLength() > 0)
			{
				nfe = (ElementImpl)nfeNL.item(0);
			}
			
			Element digestValue = null;
			NodeList digestValueNL = retDpecDocument.getElementsByTagName("DigestValue");
			if(digestValueNL.getLength()>0)
			{
				digestValue = (ElementImpl)digestValueNL.item(digestValueNL.getLength()-1);
			}
			Element infDPECReg = null;
			NodeList infDPECRegNL = retDpecDocument.getElementsByTagName("infDPECReg");
			if(infDPECRegNL.getLength()>0)
			{
				infDPECReg = (ElementImpl)infDPECRegNL.item(0);
			}
			Element tpAmbTemp = null;
			NodeList tpAmbNL = infDPECReg.getElementsByTagName("tpAmb");
			if(tpAmbNL.getLength()>0)
			{
				tpAmbTemp = (ElementImpl)tpAmbNL.item(0);
			}
			Element verAplicTemp = null;
			NodeList verAplicNL = infDPECReg.getElementsByTagName("verAplic");
			if(verAplicNL.getLength()>0)
			{
				verAplicTemp = (ElementImpl)verAplicNL.item(0);
			}
			Element chNFeTemp = null;
			NodeList chNFeNL = infDPECReg.getElementsByTagName("chNFe");
			if(chNFeNL.getLength()>0)
			{
				chNFeTemp = (ElementImpl)chNFeNL.item(0);
			}
			Element dhRegDPEC = null;
			NodeList dhRegDPECNL = infDPECReg.getElementsByTagName("dhRegDPEC");
			if(dhRegDPECNL.getLength()>0)
			{
				dhRegDPEC = (ElementImpl)dhRegDPECNL.item(0);
			}
			Element nRegDPEC = null;
			NodeList nRegDPECNL = infDPECReg.getElementsByTagName("nRegDPEC");
			if(nRegDPECNL.getLength()>0)
			{
				nRegDPEC = (ElementImpl)nRegDPECNL.item(0);
			}
			Element cStatTemp = null;
			NodeList cStatNL = infDPECReg.getElementsByTagName("cStat");
			if(cStatNL.getLength()>0)
			{
				cStatTemp = (ElementImpl)cStatNL.item(0);
			}
			Element xMotivoTemp = null;
			NodeList xMotivoNL = infDPECReg.getElementsByTagName("xMotivo");
			if(xMotivoNL.getLength()>0)
			{
				xMotivoTemp = (ElementImpl)xMotivoNL.item(0);
			}
			
			Element protNFe = nfeProcDocument.createElement("protNFe");
			protNFe.setAttribute("versao", "2.00");
			Element infProt = nfeProcDocument.createElement("infProt");
			infProt.setAttribute("Id", infDPECReg.getAttribute("Id"));
			Element tpAmb = nfeProcDocument.createElement("tpAmb");
			tpAmb.setTextContent(tpAmbTemp.getTextContent());
			Element verAplic = nfeProcDocument.createElement("verAplic");
			verAplic.setTextContent(verAplicTemp.getTextContent());
			Element chNFe = nfeProcDocument.createElement("chNFe");
			chNFe.setTextContent(chNFeTemp.getTextContent());
			Element dhRecbto = nfeProcDocument.createElement("dhRecbto");
			dhRecbto.setTextContent(dhRegDPEC.getTextContent());
			Element nProt = nfeProcDocument.createElement("nProt");
			nProt.setTextContent(nRegDPEC.getTextContent());
			Element digVal = nfeProcDocument.createElement("digVal");
			digVal.setTextContent(digestValue.getTextContent());
			Element cStat = nfeProcDocument.createElement("cStat");
			cStat.setTextContent(cStatTemp.getTextContent());
			Element xMotivo = nfeProcDocument.createElement("xMotivo");
			xMotivo.setTextContent(xMotivoTemp.getTextContent());
			
			infProt.appendChild(tpAmb);
			infProt.appendChild(verAplic);
			infProt.appendChild(chNFe);
			infProt.appendChild(dhRecbto);
			infProt.appendChild(nProt);
			infProt.appendChild(digVal);
			infProt.appendChild(cStat);
			infProt.appendChild(xMotivo);
			protNFe.appendChild(infProt);
			
			Element nfeProcNFe = (ElementImpl)nfeProcDocument.importNode(nfe, true);
			nfeProc.appendChild(nfeProcNFe);
			nfeProc.appendChild(protNFe);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Erro ao gerar nfeProc ",e1);
		}
		return DomHelper.docToXML(nfeProcDocument).toString();
	}
}
