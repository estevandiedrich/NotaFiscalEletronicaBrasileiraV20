package br.com.hs.nfe.dom.builder;

import java.io.ByteArrayOutputStream;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Nfe;

public class ConsStatServDocumentBuilder {
	public static synchronized String consStatServDocumentBuilder(Nfe e)
	{
		Document doc = DomHelper.createEmptyDocument();
		Element consStatServ = (ElementImpl)doc.createElement("consStatServ");
		consStatServ.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		consStatServ.setAttribute("versao", "2.00");
		//consStatServ.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
		//consStatServ.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Element tpAmb = (ElementImpl)doc.createElement("tpAmb");
		tpAmb.setTextContent(e.getTpAmb().toString());
		Element cUF = (ElementImpl)doc.createElement("cUF");
		cUF.setTextContent(e.getCuf());
		Element xServ = (ElementImpl)doc.createElement("xServ");
		xServ.setTextContent("STATUS");
		consStatServ.appendChild(tpAmb);
		consStatServ.appendChild(cUF);
		consStatServ.appendChild(xServ);
		doc.appendChild(consStatServ);
		ByteArrayOutputStream baos = DomHelper.docToXML(doc);
		
		return baos.toString();
	}
}
