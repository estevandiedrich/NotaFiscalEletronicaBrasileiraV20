package br.com.hs.nfe.compiler;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Txt2XmlCancNFeParser {
	public static final Logger logger = Logger.getLogger("Txt2XmlCancNFeParser");
	public static synchronized void txt2XmlCancNFeParser(String txtLine,Document xml)
	{
		String[] tokens = txtLine.split(";");
		if(tokens[0].trim().equalsIgnoreCase("0000"))
		{
			logger.info("Contem : 0000");
			if(tokens[1].trim().equalsIgnoreCase("2.00"))
			{
				logger.info("Contem : 2.00");
				if(tokens[2].trim().equalsIgnoreCase("CANCELAMENTO"))
				{
					logger.info("Contem : Cancelamento");
					Element cancNFe = (org.apache.xerces.dom.ElementImpl)xml.createElement("cancNFe");
					cancNFe.setAttribute("versao","2.00");
					cancNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					xml.appendChild(cancNFe);
				}
				else
				{
					
				}
			}
			else
			{
				
			}
		}
		else
		{
			if(tokens[0].trim().equalsIgnoreCase("1000"))
			{
				logger.info("Contem : 1000");
				Element infCanc = (org.apache.xerces.dom.ElementImpl)xml.createElement("infCanc");
				logger.info("Id "+tokens[1].trim());
				infCanc.setAttribute("Id", tokens[1].trim());
				NodeList cancNFeNL = xml.getElementsByTagName("cancNFe");
				if(cancNFeNL.getLength() > 0)
				{
					Element cancNFe = (ElementImpl)cancNFeNL.item(cancNFeNL.getLength()-1);
					cancNFe.appendChild(infCanc);
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("1100"))
				{
					logger.info("Contem : 1100");
					Element tpAmb = (ElementImpl)xml.createElement("tpAmb");
					tpAmb.setTextContent(tokens[1].trim());
					Element xServ = (ElementImpl)xml.createElement("xServ");
					xServ.setTextContent(tokens[2].trim());
					Element chNFe = (ElementImpl)xml.createElement("chNFe");
					chNFe.setTextContent(tokens[3].trim());
					Element nProt = (ElementImpl)xml.createElement("nProt");
					nProt.setTextContent(tokens[4].trim());
					Element xJust = (ElementImpl)xml.createElement("xJust");
					xJust.setTextContent(tokens[5].trim());
					
					NodeList infCancNL = xml.getElementsByTagName("infCanc");
					if(infCancNL.getLength() > 0)
					{
						Element infCanc = (Element)infCancNL.item(infCancNL.getLength()-1);
						infCanc.appendChild(tpAmb);
						infCanc.appendChild(xServ);
						infCanc.appendChild(chNFe);
						infCanc.appendChild(nProt);
						infCanc.appendChild(xJust);
					}
				}
				else
				{
					
				}
			}
		}
	}
}
