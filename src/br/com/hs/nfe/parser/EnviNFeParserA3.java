package br.com.hs.nfe.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.util.GeradorChaveAcesso;
import br.com.hs.nfe.util.TrataCaracter;
import br.gov.sp.fazenda.dsen.common.to.NotaFiscalTO;
import br.gov.sp.fazenda.dsen.model.business.expimp.DSENRegistroNotaFiscalConverter;
import br.gov.sp.fazenda.dsen.model.business.util.NotaFiscalBuilderBusiness;
import br.gov.sp.fazenda.dsge.common.to.Registro;
import br.gov.sp.fazenda.dsge.common.util.NumberHelper;
import br.gov.sp.fazenda.dsge.common.util.NumberHelper.PreDefinedNumberFormatEnum;

public class EnviNFeParserA3 {
	private static final Logger logger = Logger.getLogger("EnviNFeParserA3");
	private static GeradorChaveAcesso geradorChaveAcesso = new GeradorChaveAcesso();
	private static NotaFiscalBuilderBusiness notaFiscalBuilderBusiness = new NotaFiscalBuilderBusiness();
	private static NumberHelper helper = new NumberHelper(PreDefinedNumberFormatEnum.EN_US);
	public static void main(String... args)
	{
//		File file = new File("C:\\dev.estevan\\workspace-java\\NotaFiscalEletronicaBrasileiraV20\\resources\\NF011247.TXT");
//		Document xmlDoc = DomHelper.createEmptyDocument();
//		txt2XmlParser(file,xmlDoc);
		NumberHelper helper = new NumberHelper(PreDefinedNumberFormatEnum.EN_US);
		System.out.println(helper.toString(helper.getBigDecimal("1115.62"), 1, 9, 2, 2, RoundingMode.CEILING));
//        BigDecimal big = helper.getBigDecimal("$133,333,333.33");
//        NumberHelper helper2 = new NumberHelper(PreDefinedNumberFormatEnum.EN_US_GROUPING_CURRENCY_SYMBOL);
//        String bigS = helper2.toString(big, -1, -1, 8, 8, RoundingMode.DOWN);
//        System.out.println((new StringBuilder()).append("bigS=").append(bigS).toString());
//        System.out.println(helper2.getBigDecimal(bigS));
	}
	public static void txt2XmlParser(File file,Document xmlDoc,HashMap<String, String> params)
    {
        try
        {
        	Element enviNFeElement = (org.apache.xerces.dom.ElementImpl)xmlDoc.createElement("enviNFe");
            enviNFeElement.setAttribute("versao","2.00");
            enviNFeElement.setAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
			Element idLote = (ElementImpl)xmlDoc.createElement("idLote");
			idLote.setTextContent("000000000000001");
			enviNFeElement.appendChild(idLote);
			
            DSENRegistroNotaFiscalConverter converter = new DSENRegistroNotaFiscalConverter();
            //File file = new File("C:/dev.estevan/workspace-java/NotaFiscalEletronicaBrasileiraV20/resources/NF011247.TXT");
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            Registro baseC = converter.getRegistroFromTXT(scanner);
            List errors = new ArrayList();
            baseC.getAllErrors(errors);
            String s;
            for(Iterator i$ = errors.iterator(); i$.hasNext(); System.out.println(s))
                s = (String)i$.next();

            System.out.println("------------------------INICIO-------------------111111111-------------------------------------------------");
            String txt = baseC.toString();
            System.out.println(txt);
            System.out.println("-------------------------------------------111111111-------------------------------------------------");
            String xml = baseC.toXML();
            xml = xml.replace("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
            System.out.println(xml);
            System.out.println("-------------------------FIM------------------111111111-------------------------------------------------");
            
            xml = TrataCaracter.trata(xml);
            NotaFiscalTO notaFiscalTO = new NotaFiscalTO();
            notaFiscalTO.setDocXmlString(xml);
            
//            notaFiscalBuilderBusiness.calcularValorCIDE(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorCOFINS(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorCOFINSST(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorICMS(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorICMSST(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorIPI(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorISSQN(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorPIS(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorPISST(notaFiscalTO);
//            notaFiscalBuilderBusiness.calcularValorTotalNotaFiscal(notaFiscalTO);
            notaFiscalBuilderBusiness.calcularNotaFiscalTotais(notaFiscalTO);
            xml = notaFiscalTO.getDocXmlString();
            ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
            Document nfeDocument = DomHelper.createDocument(is);
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(is);
            
            geradorChaveAcesso.construirChaveAcesso(nfeDocument);
            NodeList destNL = nfeDocument.getElementsByTagName("dest");
            if(destNL.getLength() > 0)
            {
            	Element dest = (ElementImpl)destNL.item(0);
            	NodeList emailNL = dest.getElementsByTagName("email");
            	if(emailNL.getLength() > 0)
            	{
            		Element email = (ElementImpl)emailNL.item(0);
            		params.put("email", email.getTextContent());
            	}
            }

			NodeList nfeNL = nfeDocument.getElementsByTagName("NFe");
			Element nfe = null;
			if(nfeNL.getLength() > 0)
			{
				nfe = (ElementImpl)nfeNL.item(0);
			}
			
			Element nfeElement = (ElementImpl)xmlDoc.importNode(nfe, true);
			enviNFeElement.appendChild(nfeElement);
			xmlDoc.appendChild(enviNFeElement);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
