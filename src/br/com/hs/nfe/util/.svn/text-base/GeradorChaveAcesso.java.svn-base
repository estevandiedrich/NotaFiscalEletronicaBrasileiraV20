package br.com.hs.nfe.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.xpath.XPathUtil;

public class GeradorChaveAcesso {
	private static final Logger logger = Logger.getLogger("GeradorChaveAcesso");
	public String construirChaveDados(String xml) throws Exception
	{
		String chaveDados = "";
		String ufDestinatario = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:dest/:enderDest/:UF");
		String tpEmis = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:ide/:tpEmis");
		String cnpjDestinatario = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:dest/:CNPJ");
		String vTotalNF = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:total/:ICMSTot/:vNF");
		String vICMS = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:total/:ICMSTot/:vICMS");
		if(ufDestinatario.equalsIgnoreCase("SC"))
		{
			chaveDados += "42";
		}
		chaveDados += tpEmis;
		chaveDados += cnpjDestinatario;
		vTotalNF = vTotalNF.replace(".", "");
		vTotalNF = "00000000000000".substring(0,14 - vTotalNF.length())+vTotalNF;
		chaveDados += vTotalNF;
		if(vICMS != null && !vICMS.equalsIgnoreCase("") &&!vICMS.equalsIgnoreCase("0.00"))
		{
			chaveDados += "1";
		}
		else
		{
			chaveDados += "2";
		}
		String vICMSST = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:total/:ICMSTot/:vICMSST");
		if(vICMSST != null && !vICMSST.equalsIgnoreCase("") && !vICMSST.equalsIgnoreCase("0.00"))
		{
			chaveDados += "1";
		}
		else
		{
			chaveDados += "2";
		}
		String dEmi = XPathUtil.solveXPath(new ByteArrayInputStream(xml.getBytes()), "//:nfeProc/:NFe/:infNFe/:ide/:dEmi").substring(8);
		chaveDados += dEmi;
		chaveDados += insereDigitoVerificador(chaveDados,22);
		return chaveDados;
	}
	public String construirChaveAcessoScan(org.w3c.dom.Document doc,String tpEmisTemp,String chaveAcesso,Estabelecimento config)
	{
		NodeList serie = doc.getElementsByTagName("serie");
		int serieAntiga = 900;
		if(serie.getLength()>0)
		{
			serieAntiga += Integer.parseInt(serie.item(0).getTextContent());
			if(serieAntiga >= 999)
			{
				serie.item(0).setTextContent(String.valueOf(999));
			}
			else
			{
				serie.item(0).setTextContent(String.valueOf(serieAntiga));
			}
			NodeList ideNL = doc.getElementsByTagName("ide");
			if(ideNL.getLength() > 0)
			{
				Element ide = (Element)ideNL.item(0);
				Element dhCont = doc.createElement("dhCont");
				dhCont.setTextContent(NFeSimpleDateFormat.getInstance().parse(new Date()));
				Element xJust = doc.createElement("xJust");
				xJust.setTextContent("Falha de acesso ao sefaz da uf");
				ide.appendChild(dhCont);
				ide.appendChild(xJust);
			}
		}
		return this.construirChaveAcesso(doc, tpEmisTemp, chaveAcesso, config);
	}
	public void construirChaveAcesso(org.w3c.dom.Document doc)
	{
		String nfeId = "";
		
		NodeList cUF = doc.getElementsByTagName("cUF");
		if(cUF.getLength()>0)
		{
			nfeId += cUF.item(0).getTextContent();
		}
		NodeList dEmi = doc.getElementsByTagName("dEmi");
		if(dEmi.getLength()>0)
		{
			nfeId += dEmi.item(0).getTextContent().substring(2,7).replace("-","");
		}
		NodeList cnpj = doc.getElementsByTagName("CNPJ");

		nfeId += cnpj.item(0).getTextContent();
		NodeList mod = doc.getElementsByTagName("mod");
		if(mod.getLength()>0)
		{
			nfeId += mod.item(0).getTextContent();
		}
		NodeList serie = doc.getElementsByTagName("serie");
		String serieTemp = "";
		if(serie.getLength()>0)
		{
			serieTemp = serie.item(0).getTextContent();
			serieTemp = "000".substring(0, 3 - serieTemp.length()) + serieTemp;
		}
		nfeId += serieTemp;
		NodeList nNF = doc.getElementsByTagName("nNF");
		String nNFTemp = "";
		if(nNF.getLength()>0)
		{
			nNFTemp = nNF.item(0).getTextContent();
			String nNFTemp2 = "";
			for(int i = 0;i < nNFTemp.length();i++)
			{
				if(nNFTemp.charAt(i) != '0')
				{
					nNFTemp2 = nNFTemp.substring(i, nNFTemp.length());
					break;
				}
			}
			nNF.item(0).setTextContent(nNFTemp2);
			nNFTemp = "000000000".substring(0, 9 - nNFTemp.length()) + nNFTemp;
		}
		nfeId += nNFTemp;
		String tpEmisTemp = "";
		NodeList tpEmis = doc.getElementsByTagName("tpEmis");
		if(tpEmis.getLength()>0)
		{
			tpEmisTemp = tpEmis.item(0).getTextContent();
		}
		nfeId += tpEmisTemp;
		NodeList cNF = doc.getElementsByTagName("cNF");
		String cNFTemp = "";
		if(cNF.getLength()>0)
		{
			cNFTemp += cNF.item(0).getTextContent();
			if(cNFTemp.equalsIgnoreCase(""))
			{
				cNFTemp = Long.toString(Math.round(Math.random()*99999999));
				cNFTemp = "00000000".substring(0, 8 - cNFTemp.length()) + cNFTemp;
				cNF.item(0).setTextContent(cNFTemp);
			}
			
		}
		nfeId += cNFTemp;
		if(nfeId.length() == 43)
		{
			logger.debug("Gerando Digito verificador");
			nfeId = insereDigitoVerificador(nfeId,43);
			
		}
		else
		{
			logger.error("Não é possivel gerar digito verificado devido a incosistencia das informações do XML");
		}
		
		nfeId = "NFe" + nfeId;
		
		NodeList cDV = doc.getElementsByTagName("cDV");
		if(cDV.getLength()>0)
			cDV.item(0).setTextContent(nfeId.substring(nfeId.length()-1));
		
		Element infNFe = null;
		NodeList infNFeNL = doc.getElementsByTagName("infNFe");
		if(infNFeNL.getLength()>0)
		{
			infNFe = (Element)infNFeNL.item(0);
			infNFe.setAttribute("Id",nfeId);
		}
	}
	public String construirChaveAcesso(org.w3c.dom.Document doc,String tpEmisTemp,String chaveAcesso,Estabelecimento config)
	{
		String nfeId = "";
		
		NodeList cUF = doc.getElementsByTagName("cUF");
		if(cUF.getLength()>0)
		{
			nfeId += cUF.item(0).getTextContent();
		}
		NodeList dEmi = doc.getElementsByTagName("dEmi");
		if(dEmi.getLength()>0)
		{
			nfeId += dEmi.item(0).getTextContent().substring(2,7).replace("-","");
		}
		NodeList cnpj = doc.getElementsByTagName("CNPJ");

		nfeId += cnpj.item(0).getTextContent();
		NodeList mod = doc.getElementsByTagName("mod");
		if(mod.getLength()>0)
		{
			nfeId += mod.item(0).getTextContent();
		}
		NodeList serie = doc.getElementsByTagName("serie");
		String serieTemp = "";
		if(serie.getLength()>0)
		{
			serieTemp = serie.item(0).getTextContent();
			serieTemp = "000".substring(0, 3 - serieTemp.length()) + serieTemp;
		}
		nfeId += serieTemp;
		NodeList nNF = doc.getElementsByTagName("nNF");
		String nNFTemp = "";
		if(nNF.getLength()>0)
		{
			nNFTemp = nNF.item(0).getTextContent();
			String nNFTemp2 = "";
			for(int i = 0;i < nNFTemp.length();i++)
			{
				if(nNFTemp.charAt(i) != '0')
				{
					nNFTemp2 = nNFTemp.substring(i, nNFTemp.length());
					break;
				}
			}
			nNF.item(0).setTextContent(nNFTemp2);
			nNFTemp = "000000000".substring(0, 9 - nNFTemp.length()) + nNFTemp;

		}
		nfeId += nNFTemp;
		NodeList tpEmis = doc.getElementsByTagName("tpEmis");
		if(tpEmis.getLength()>0)
		{
			tpEmis.item(0).setTextContent(tpEmisTemp);
		}
		nfeId += tpEmisTemp;
		NodeList cNF = doc.getElementsByTagName("cNF");
		String cNFTemp = "";
		if(cNF.getLength()>0)
		{
			cNFTemp += cNF.item(0).getTextContent();
			cNFTemp = "00000000".substring(0, 8 - cNFTemp.length()) + cNFTemp;
		}
		nfeId += cNFTemp;
		if(nfeId.length() == 43)
		{
			logger.debug("Gerando Digito verificador");
			nfeId = insereDigitoVerificador(nfeId,43);
			
		}
		else
		{
			logger.error("Não é possivel gerar digito verificado devido a incosistencia das informações do XML");
			return "";
		}
		
		nfeId = "NFe" + nfeId;
		
		NodeList cDV = doc.getElementsByTagName("cDV");
		if(cDV.getLength()>0)
			cDV.item(0).setTextContent(nfeId.substring(nfeId.length()-1));
		
		Element infNFe = null;
		NodeList infNFeNL = doc.getElementsByTagName("infNFe");
		if(infNFeNL.getLength()>0)
		{
			infNFe = (Element)infNFeNL.item(0);
			infNFe.setAttribute("Id",nfeId);
		}
		this.geraArquivoRel(chaveAcesso, nfeId, config);
		return nfeId;
	}
	private boolean geraArquivoRel(String chaveVelha,String chaveNova,Estabelecimento config)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(chaveVelha);
		sb.append(";");
		sb.append(chaveNova);
		return FileManager.getInstance().saveFile(config.getConsReciNFeTXT()+File.separatorChar+chaveVelha+"-rel.txt", sb.toString());
	}
	private String insereDigitoVerificador(String nfe,int lenght)   
	{   
	    int[] pesos={4,3,2,9,8,7,6,5};   
	    int somaPonderada = 0;   
	    for (int i = 0; i < lenght; i++)   
	        somaPonderada += pesos[i%8]*(Integer.parseInt(nfe.substring(i, i+1)));   
	    if((somaPonderada%11) == 0 || (somaPonderada%11) == 1)
	    	return nfe+0;
	    else
	    	return nfe+(11-(somaPonderada%11));   
	}  
}
