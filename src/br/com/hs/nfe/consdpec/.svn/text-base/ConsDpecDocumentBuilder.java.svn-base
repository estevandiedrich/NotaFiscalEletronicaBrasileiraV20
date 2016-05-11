package br.com.hs.nfe.consdpec;

import java.io.ByteArrayOutputStream;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.hs.nfe.dom.DomHelper;


public class ConsDpecDocumentBuilder {
	public static synchronized String consDpecDocumentBuilder(String chaveAcesso,String tpAmb,String verAplic)
	{
		Document doc = DomHelper.createEmptyDocument();
		Element consDPEC = (ElementImpl)doc.createElement("consDPEC");
		consDPEC.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		consDPEC.setAttribute("versao", "1.01");
		//consSitNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
		//consSitNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Element tpAmbElement = (ElementImpl)doc.createElement("tpAmb");
		tpAmbElement.setTextContent(tpAmb);
		consDPEC.appendChild(tpAmbElement);
		Element verAplicElement = (ElementImpl)doc.createElement("verAplic");
		verAplicElement.setTextContent(verAplic);
		consDPEC.appendChild(verAplicElement);
		Element chNFe = (ElementImpl)doc.createElement("chNFe");
		chNFe.setTextContent(chaveAcesso.substring(chaveAcesso.indexOf("e")+1));
		consDPEC.appendChild(chNFe);
		doc.appendChild(consDPEC);
		
		ByteArrayOutputStream baos = DomHelper.docToXML(doc); 
		return baos.toString();
	}
}
