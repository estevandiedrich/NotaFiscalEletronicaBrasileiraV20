package br.com.hs.nfe.compiler;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Txt2XmlInutNFeParser {
	public static final Logger logger = Logger.getLogger("Txt2XmlInutNFeParser");
	public static synchronized void txt2XmlInutNFeParser(String txtLine,Document xml)
	{
		String[] tokens = txtLine.split(";");
		if(tokens[0].trim().equalsIgnoreCase("0000"))
		{
			logger.info("Contem : 0000");
			if(tokens[1].trim().equalsIgnoreCase("2.00"))
			{
				logger.info("Contem : 2.00");
				if(tokens[2].trim().equalsIgnoreCase("INUTILIZACAO"))
				{
					logger.info("Contem : Inutilizacao");
					Element inutNFe = (org.apache.xerces.dom.ElementImpl)xml.createElement("inutNFe");
					inutNFe.setAttribute("versao","2.00");
					inutNFe.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					xml.appendChild(inutNFe);
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
				Element infInut = (org.apache.xerces.dom.ElementImpl)xml.createElement("infInut");
				logger.info("Id "+tokens[1].trim());
				infInut.setAttribute("Id", tokens[1].trim());
				NodeList inutNFeNL = xml.getElementsByTagName("inutNFe");
				if(inutNFeNL.getLength() > 0)
				{
					Element inutNFe = (ElementImpl)inutNFeNL.item(inutNFeNL.getLength()-1);
					inutNFe.appendChild(infInut);
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("1100"))
				{
					logger.info("Contem : 1100");
					Element tpAmb = (org.apache.xerces.dom.ElementImpl)xml.createElement("tpAmb");
					tpAmb.setTextContent(tokens[1].trim());
					Element xServ = (org.apache.xerces.dom.ElementImpl)xml.createElement("xServ");
					xServ.setTextContent(tokens[2].trim());
					Element cUF = (org.apache.xerces.dom.ElementImpl)xml.createElement("cUF");
					cUF.setTextContent(tokens[3].trim());
					Element ano = (org.apache.xerces.dom.ElementImpl)xml.createElement("ano");
					ano.setTextContent(tokens[4].trim());
					Element CNPJ = (org.apache.xerces.dom.ElementImpl)xml.createElement("CNPJ");
					CNPJ.setTextContent(tokens[5].trim());
					Element mod = (org.apache.xerces.dom.ElementImpl)xml.createElement("mod");
					mod.setTextContent(tokens[6].trim());
					Element serie = (org.apache.xerces.dom.ElementImpl)xml.createElement("serie");
					serie.setTextContent(tokens[7].trim());
					Element nNFIni = (org.apache.xerces.dom.ElementImpl)xml.createElement("nNFIni");
					nNFIni.setTextContent(tokens[8].trim());
					Element nNFFin = (org.apache.xerces.dom.ElementImpl)xml.createElement("nNFFin");
					nNFFin.setTextContent(tokens[9].trim());
					Element xJust = (org.apache.xerces.dom.ElementImpl)xml.createElement("xJust");
					xJust.setTextContent(tokens[10].trim());
					
					NodeList infInutNL = xml.getElementsByTagName("infInut");
					if(infInutNL.getLength() > 0)
					{
						Element infInut = (Element)infInutNL.item(infInutNL.getLength()-1);
						infInut.appendChild(tpAmb);
						infInut.appendChild(xServ);
						infInut.appendChild(cUF);
						infInut.appendChild(ano);
						infInut.appendChild(CNPJ);
						infInut.appendChild(mod);
						infInut.appendChild(serie);
						infInut.appendChild(nNFIni);
						infInut.appendChild(nNFFin);
						infInut.appendChild(xJust);
					}
				}
				else
				{
					
				}
			}
		}
	}
}
