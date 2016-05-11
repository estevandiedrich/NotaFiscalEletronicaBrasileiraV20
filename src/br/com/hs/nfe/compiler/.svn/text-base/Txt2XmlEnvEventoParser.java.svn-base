package br.com.hs.nfe.compiler;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Txt2XmlEnvEventoParser {
	public static final Logger logger = Logger.getLogger("Txt2XmlEnvEventoParser");
	public static synchronized void txt2XmlEnvEventoParser(String txtLine,Document xml,HashMap<String, String> params)
	{
		String[] tokens = txtLine.split(";");
		if(tokens[0].trim().equalsIgnoreCase("0000"))
		{
			logger.info("Contem : 0000");
			if(tokens[1].equalsIgnoreCase("1.00"))
			{
				logger.info("Contem : 1.00");
				if(tokens[2].equalsIgnoreCase("EVENTO"))
				{
					logger.info("Contem : EVENTO");
					Element envEvento = (org.apache.xerces.dom.ElementImpl)xml.createElement("envEvento");
					envEvento.setAttribute("versao","1.00");
					envEvento.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
					//enviNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
					//enviNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
					xml.appendChild(envEvento);
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
				NodeList envEventoNL = xml.getElementsByTagName("envEvento");
				logger.info("idLote : "+tokens[1].trim());
				Element idLote = (ElementImpl)xml.createElement("idLote");
				idLote.setTextContent(tokens[1].trim());
				if(envEventoNL.getLength() > 0)
				{
					Element envEvento = (ElementImpl)envEventoNL.item(envEventoNL.getLength()-1);
					envEvento.appendChild(idLote);
				}
			}
			else
			{
				if(tokens[0].trim().equalsIgnoreCase("2000"))
				{
					logger.info("Contem : 2000");
					if(tokens[1].trim().equalsIgnoreCase("1.00"))
					{
						logger.info("Contem : 1.00");
						Element evento = (ElementImpl)xml.createElement("evento");
						NodeList envEventoNL = xml.getElementsByTagName("envEvento");
						evento.setAttribute("versao", "1.00");
						evento.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
						if(envEventoNL.getLength() > 0)
						{
							Element envEvento = (ElementImpl)envEventoNL.item(envEventoNL.getLength()-1);
							envEvento.appendChild(evento);
						}
					}
					else
					{
						
					}
				}
				else
				{
					if(tokens[0].trim().equalsIgnoreCase("2100"))
					{
						logger.info("Contem : 2100");
						Element infEvento = (ElementImpl)xml.createElement("infEvento");
						infEvento.setAttribute("Id", tokens[1].trim());
						
						Element cOrgao = (ElementImpl)xml.createElement("cOrgao");
						cOrgao.setTextContent(tokens[2].trim());
						infEvento.appendChild(cOrgao);
						Element tpAmb = (ElementImpl)xml.createElement("tpAmb");
						tpAmb.setTextContent(tokens[3].trim());
						infEvento.appendChild(tpAmb);
						if(tokens[4].trim().length() == 14)
						{
							Element cnpj = (ElementImpl)xml.createElement("CNPJ");
							cnpj.setTextContent(tokens[4].trim());
							infEvento.appendChild(cnpj);
						}
						else
						{
							if(tokens[4].trim().length() == 11)
							{
								Element cpf = (ElementImpl)xml.createElement("CPF");
								cpf.setTextContent(tokens[4].trim());
								infEvento.appendChild(cpf);
							}
						}
						Element chNFe = (ElementImpl)xml.createElement("chNFe");
						chNFe.setTextContent(tokens[5].trim());
						infEvento.appendChild(chNFe);
						Element dhEvento = (ElementImpl)xml.createElement("dhEvento");
						dhEvento.setTextContent(tokens[6].trim());
						infEvento.appendChild(dhEvento);
						Element tpEvento = (ElementImpl)xml.createElement("tpEvento");
						tpEvento.setTextContent(tokens[7].trim());
						infEvento.appendChild(tpEvento);
						Element nSeqEvento = (ElementImpl)xml.createElement("nSeqEvento");
						nSeqEvento.setTextContent(tokens[8].trim());
						infEvento.appendChild(nSeqEvento);
						Element verEvento = (ElementImpl)xml.createElement("verEvento");
						verEvento.setTextContent(tokens[9].trim());
						infEvento.appendChild(verEvento);
						NodeList eventoNL = xml.getElementsByTagName("evento");
						if(eventoNL.getLength() > 0)
						{
							Element evento = (ElementImpl)eventoNL.item(eventoNL.getLength()-1);
							evento.appendChild(infEvento);
						}
					}
					else
					{
						if(tokens[0].trim().equalsIgnoreCase("3000"))
						{
							logger.info("Contem : 3000");
							NodeList infEventoNL = xml.getElementsByTagName("infEvento");
							
							Element detEvento = (ElementImpl)xml.createElement("detEvento");
							detEvento.setAttribute("versao",tokens[1].trim());
							Element descEvento = (ElementImpl)xml.createElement("descEvento");
							descEvento.setTextContent(tokens[2].trim());
							detEvento.appendChild(descEvento);
							Element xCorrecao = (ElementImpl)xml.createElement("xCorrecao");
							xCorrecao.setTextContent(tokens[3].trim());
							detEvento.appendChild(xCorrecao);
							Element xCondUso = (ElementImpl)xml.createElement("xCondUso");
							//xCondUso.setTextContent("A Carta de Correção é disciplinada pelo § 1º-A do art. 7º do Convênio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularização de erro ocorrido na emissão de documento fiscal, desde que o erro não esteja relacionado com: I - as variáveis que determinam o valor do imposto tais como: base de cálculo, alíquota, diferença de preço, quantidade, valor da operação ou da prestação; II - a correção de dados cadastrais que implique mudança do remetente ou do destinatário; III - a data de emissão ou de saída.");							
							//xCondUso.setTextContent("A Carta de Corre\u00e7\u00e3o \u00e9 disciplinada pelo \u00a7 1\u00ba-A do art. 7\u00ba do Conv\u00eanio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regulariza\u00e7\u00e3o de erro ocorrido na emiss\u00e3o de documento fiscal, desde que o erro n\u00e3o esteja relacionado com: I - as vari\u00e1veis que determinam o valor do imposto tais como: base de c\u00e1lculo, al\u00edquota, diferen\u00e7a de pre\u00e7o, quantidade, valor da opera\u00e7\u00e3o ou da presta\u00e7\u00e3o; II - a corre\u00e7\u00e3o de dados cadastrais que implique mudan\u00e7a do remetente ou do destinat\u00e1rio; III - a data de emiss\u00e3o ou de sa\u00edda.");							
							xCondUso.setTextContent("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.");							
							detEvento.appendChild(xCondUso);
							
							if(infEventoNL.getLength() > 0)
							{
								Element infEvento = (ElementImpl)infEventoNL.item(infEventoNL.getLength()-1);
								infEvento.appendChild(detEvento);
							}
						}
						else
						{
							logger.info("Token inesperado : "+tokens[0]);
						}
					}
				}
			}
		}
	}
}
