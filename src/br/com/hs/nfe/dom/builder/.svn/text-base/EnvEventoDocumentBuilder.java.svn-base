package br.com.hs.nfe.dom.builder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.util.NFeSimpleDateFormat;
import br.com.hs.nfe.xpath.XPathUtil;

public class EnvEventoDocumentBuilder {
	public static synchronized String envEventoDocumentBuilder(String e) throws Exception
	{
		String chNFe = XPathUtil.solveXPath(new ByteArrayInputStream(e.getBytes()), "//:nfeProc/:NFe/:infNFe/@Id");
		String cUF = XPathUtil.solveXPath(new ByteArrayInputStream(e.getBytes()), "//:nfeProc/:NFe/:infNFe/:ide/:cUF");
		String tpAmb = XPathUtil.solveXPath(new ByteArrayInputStream(e.getBytes()), "//:nfeProc/:NFe/:infNFe/:ide/:tpAmb");
		String cnpj = XPathUtil.solveXPath(new ByteArrayInputStream(e.getBytes()), "//:nfeProc/:NFe/:infNFe/:dest/:CNPJ");
		String cpf = XPathUtil.solveXPath(new ByteArrayInputStream(e.getBytes()), "//:nfeProc/:NFe/:infNFe/:dest/:CPF");
		
		Document doc = DomHelper.createEmptyDocument();
		Element envEvento = (org.apache.xerces.dom.ElementImpl)doc.createElement("envEvento");
		envEvento.setAttribute("versao","1.00");
		envEvento.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		//enviNFe.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		//enviNFe.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
		Element idLote = (ElementImpl)doc.createElement("idLote");
		idLote.setTextContent("000000000000001");
		Element evento = (ElementImpl)doc.createElement("evento");
		evento.setAttribute("versao", "1.00");
		evento.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		Element infEvento = (ElementImpl)doc.createElement("infEvento");
		infEvento.setAttribute("Id", "ID110110"+chNFe.substring(3)+"01");
		Element cOrgao = (ElementImpl)doc.createElement("cOrgao");
		cOrgao.setTextContent(cUF);
		infEvento.appendChild(cOrgao);
		Element tpAmbElement = (ElementImpl)doc.createElement("tpAmb");
		tpAmbElement.setTextContent(tpAmb);
		infEvento.appendChild(tpAmbElement);
		if(cnpj != null && !cnpj.equalsIgnoreCase(""))
		{
			Element cnpjElement = (ElementImpl)doc.createElement("CNPJ");
			cnpjElement.setTextContent(cnpj);
			infEvento.appendChild(cnpjElement);
		}
		else
		{
			if(cpf != null && !cpf.equalsIgnoreCase(""))
			{
				Element cpfElement = (ElementImpl)doc.createElement("CPF");
				cpfElement.setTextContent(cpf);
				infEvento.appendChild(cpfElement);
			}
		}
		Element chNFeElement = (ElementImpl)doc.createElement("chNFe");
		chNFeElement.setTextContent(chNFe.substring(3));
		infEvento.appendChild(chNFeElement);
		Element dhEvento = (ElementImpl)doc.createElement("dhEvento");
		dhEvento.setTextContent(NFeSimpleDateFormat.getInstance().parse(new Date())+"-01:00");
		infEvento.appendChild(dhEvento);
		Element tpEvento = (ElementImpl)doc.createElement("tpEvento");
		tpEvento.setTextContent("110110");
		infEvento.appendChild(tpEvento);
		Element nSeqEvento = (ElementImpl)doc.createElement("nSeqEvento");
		nSeqEvento.setTextContent("1");
		infEvento.appendChild(nSeqEvento);
		Element verEvento = (ElementImpl)doc.createElement("verEvento");
		verEvento.setTextContent("1.00");
		infEvento.appendChild(verEvento);
		Element detEvento = (ElementImpl)doc.createElement("detEvento");
		detEvento.setAttribute("versao","1.00");
		Element descEvento = (ElementImpl)doc.createElement("descEvento");
		descEvento.setTextContent("Confirmacao de recebimento");
		detEvento.appendChild(descEvento);
		Element xCondUso = (ElementImpl)doc.createElement("xCondUso");
		xCondUso.setTextContent("A Carta de Correcao e disciplinada pelo paragrafo 1o-A do art. 7o do Convenio S/N, de 15 de dezembro de 1970 e pode ser utilizada para regularizacao de erro ocorrido na emissao de documento fiscal, desde que o erro nao esteja relacionado com: I - as variaveis que determinam o valor do imposto tais como: base de calculo, aliquota, diferenca de preco, quantidade, valor da operacao ou da prestacao; II - a correcao de dados cadastrais que implique mudanca do remetente ou do destinatario; III - a data de emissao ou de saida.");							
		detEvento.appendChild(xCondUso);
		infEvento.appendChild(detEvento);
		evento.appendChild(infEvento);
		envEvento.appendChild(idLote);
		envEvento.appendChild(evento);
		doc.appendChild(envEvento);
		
		ByteArrayOutputStream baos = DomHelper.docToXML(doc);
		return baos.toString();
	}
}
