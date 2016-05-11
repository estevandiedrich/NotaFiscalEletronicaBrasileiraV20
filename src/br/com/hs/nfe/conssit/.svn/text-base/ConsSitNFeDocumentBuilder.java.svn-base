package br.com.hs.nfe.conssit;

import java.io.ByteArrayOutputStream;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class ConsSitNFeDocumentBuilder {
	public static synchronized String consSitNFeDocumentBuilder(NFeVO e,String versao)
	{
		Document doc = DomHelper.createEmptyDocument();
		Element consSitNFe = (ElementImpl)doc.createElement("consSitNFe");
		consSitNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		consSitNFe.setAttribute("versao", versao);
		//consSitNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
		//consSitNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		Element tpAmb = (ElementImpl)doc.createElement("tpAmb");
		tpAmb.setTextContent(e.getTpAmb());
		consSitNFe.appendChild(tpAmb);
		Element xServ = (ElementImpl)doc.createElement("xServ");
		xServ.setTextContent("CONSULTAR");
		consSitNFe.appendChild(xServ);
		Element chNFe = (ElementImpl)doc.createElement("chNFe");
		chNFe.setTextContent(e.getChaveAcesso().substring(e.getChaveAcesso().indexOf("e")+1));
		consSitNFe.appendChild(chNFe);
		doc.appendChild(consSitNFe);
		
		ByteArrayOutputStream baos = DomHelper.docToXML(doc); 
		return baos.toString();
	}
}
