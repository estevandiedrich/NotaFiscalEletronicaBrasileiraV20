package br.com.hs.nfe.dom.builder;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.vo.NFeVO;

public class FsdaProcDocumentBuilder {
	private static final Logger logger = Logger.getLogger("FsdaProcDocumentBuilder");
	public String fsdaProcDocumentBuilder(NFeVO e)
	{
		Document nfeProcDocument = DomHelper.createEmptyDocument();
		Element nfeProc = (ElementImpl)nfeProcDocument.createElement("nfeProc");
		nfeProc.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		nfeProc.setAttribute("versao", "2.00");
		nfeProcDocument.appendChild(nfeProc);
		Document enviNFeDocument = null;
		try {
			enviNFeDocument = DomHelper.toDocument(e.getNfeXMLString().getBytes());
			NodeList nfeNL = enviNFeDocument.getElementsByTagName("NFe");
			Element nfe = null;
			if(nfeNL.getLength() > 0)
			{
				nfe = (ElementImpl)nfeNL.item(0);
			}
			
			Element protNFe = nfeProcDocument.createElement("protNFe");
			protNFe.setAttribute("versao", "2.00");
			Element infProt = nfeProcDocument.createElement("infProt");
			infProt.setAttribute("Id", "");
			Element tpAmb = nfeProcDocument.createElement("tpAmb");
			tpAmb.setTextContent("");
			Element verAplic = nfeProcDocument.createElement("verAplic");
			verAplic.setTextContent("");
			Element chNFe = nfeProcDocument.createElement("chNFe");
			chNFe.setTextContent("");
			Element dhRecbto = nfeProcDocument.createElement("dhRecbto");
			dhRecbto.setTextContent("");
			Element nProt = nfeProcDocument.createElement("nProt");
			nProt.setTextContent("");
			Element digVal = nfeProcDocument.createElement("digVal");
			digVal.setTextContent("");
			Element cStat = nfeProcDocument.createElement("cStat");
			cStat.setTextContent("");
			Element xMotivo = nfeProcDocument.createElement("xMotivo");
			xMotivo.setTextContent("");
			
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
			logger.error("Erro ao gerar fsdaProc ",e1);
		}
		return DomHelper.docToXML(nfeProcDocument).toString();
	}
}
