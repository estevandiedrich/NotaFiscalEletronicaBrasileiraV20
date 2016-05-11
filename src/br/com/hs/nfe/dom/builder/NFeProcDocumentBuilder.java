package br.com.hs.nfe.dom.builder;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class NFeProcDocumentBuilder {
	private static final Logger logger = Logger.getLogger("NFeProcDocumentBuilder");
	public String nfeProcDocumentBuilder(NFeVO e)
	{
		Document nfeProcDocument = DomHelper.createEmptyDocument();
		Element nfeProc = (ElementImpl)nfeProcDocument.createElement("nfeProc");
		nfeProc.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		nfeProc.setAttribute("versao", "2.00");
		nfeProcDocument.appendChild(nfeProc);
		Document enviNFeDocument = null;
		Document retConsReciNFeDocument = null;
		try {
			enviNFeDocument = DomHelper.toDocument(e.getNfeXMLString().getBytes());
			retConsReciNFeDocument = DomHelper.toDocument(e.getRetConsReciNFe().getBytes());
			NodeList nfeNL = enviNFeDocument.getElementsByTagName("NFe");
			Element nfe = null;
			if(nfeNL.getLength() > 0)
			{
				nfe = (ElementImpl)nfeNL.item(0);
			}
			NodeList protNFeNL = retConsReciNFeDocument.getElementsByTagName("protNFe");
			Element protNFe = null;
			if(protNFeNL.getLength() > 0)
			{
				protNFe = (ElementImpl)protNFeNL.item(0);
			}
			Element nfeProcNFe = (ElementImpl)nfeProcDocument.importNode(nfe, true);
			nfeProc.appendChild(nfeProcNFe);
			Element nfeProcProtNFe = (ElementImpl)nfeProcDocument.importNode(protNFe, true);
			nfeProc.appendChild(nfeProcProtNFe);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("Erro ao gerar nfeProc ",e1);
		}
		return DomHelper.docToXML(nfeProcDocument).toString();
	}
}
