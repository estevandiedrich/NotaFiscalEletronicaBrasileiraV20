package br.com.hs.nfe.dom.builder;

import java.io.ByteArrayOutputStream;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class ConsReciNFeDocumentBuilder {
	public static synchronized String consReciNFeDocumentBuilder(NFeVO e)
	{
		Document doc = DomHelper.createEmptyDocument();
		Element consReciNFe = (ElementImpl)doc.createElement("consReciNFe");
		consReciNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		consReciNFe.setAttribute("versao", "2.00");
		//consReciNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
		//consReciNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Element tpAmb = (ElementImpl)doc.createElement("tpAmb");
		tpAmb.setTextContent(e.getTpAmb());
		Element nRec = (ElementImpl)doc.createElement("nRec");
		nRec.setTextContent(e.getNRec());
		consReciNFe.appendChild(tpAmb);
		consReciNFe.appendChild(nRec);
		doc.appendChild(consReciNFe);
		ByteArrayOutputStream baos = DomHelper.docToXML(doc);
		return baos.toString();
	}
}
