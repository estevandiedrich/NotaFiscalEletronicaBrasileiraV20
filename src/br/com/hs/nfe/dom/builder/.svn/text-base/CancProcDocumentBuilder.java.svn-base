package br.com.hs.nfe.dom.builder;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class CancProcDocumentBuilder {
	private static final Logger logger = Logger.getLogger("CancProcDocumentBuilder");
	public String cancProcDocumentBuilder(NFeVO e)
	{
		Document procCancNFeDocument = DomHelper.createEmptyDocument();
		Element procCancNFe = (ElementImpl)procCancNFeDocument.createElement("procCancNFe");
		procCancNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		procCancNFe.setAttribute("versao", "2.00");
		procCancNFeDocument.appendChild(procCancNFe);
		Document cancNFeDocument = null;
		Document retCancNFeDocument = null;
		try {
			cancNFeDocument = DomHelper.toDocument(e.getCancXMLString().getBytes());
			Element cancNFeElement = null;
			NodeList cancNFeNL = cancNFeDocument.getElementsByTagName("cancNFe");
			if(cancNFeNL.getLength() > 0)
			{
				cancNFeElement = (ElementImpl)cancNFeNL.item(cancNFeNL.getLength()-1);
			}
			Element cancProcNFe = (ElementImpl)procCancNFeDocument.importNode(cancNFeElement, true);
			procCancNFe.appendChild(cancProcNFe);
			
			retCancNFeDocument = DomHelper.toDocument(e.getRetCancNFe().getBytes());
			Element retCancNFeElement = null;
			NodeList retCancNFeNL = retCancNFeDocument.getElementsByTagName("retCancNFe");
			if(retCancNFeNL.getLength() > 0)
			{
				retCancNFeElement = (ElementImpl)retCancNFeNL.item(retCancNFeNL.getLength()-1);
			}
			
			Element retCancNFe = (ElementImpl)procCancNFeDocument.importNode(retCancNFeElement, true);
			procCancNFe.appendChild(retCancNFe);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Erro ao gerar cancProc ",e1);
		}
		return DomHelper.docToXML(procCancNFeDocument).toString();
	}
}
