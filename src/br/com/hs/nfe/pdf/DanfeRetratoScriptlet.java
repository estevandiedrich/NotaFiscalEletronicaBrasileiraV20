package br.com.hs.nfe.pdf;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import net.sf.jasperreports.engine.JRScriptletException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe utilitária dentro da geração do DANFE pelo próprio JasperReport.
 * 
 * @author Endrigo
 */
public class DanfeRetratoScriptlet extends net.sf.jasperreports.engine.JRDefaultScriptlet {
	
	private static final Logger logger = Logger.getLogger("DanfeRetratoScriptlet");
	
	private static final Double ZERO = Double.valueOf(0);
	
	private static final String NULO = "NULO";
	
	/** Quantidade de caracteres de um CNPJ */
	private static final int CNPJ_LENGTH = 14;
	
	/* Dados para a formatação de um CNPJ */

	private static final int[] CNPJ_NUM_SEPARADORES = { 1, 4, 7, 11 };
	private static final char[] CNPJ_CHAR_SEPARADORES = { '.', '.', '/', '-' };
	
	/* Dados para a formatação de um CPF */

	private static final int[] CPF_NUM_SEPARADORES = { 2, 5, 8 };
	private static final char[] CPF_CHAR_SEPARADORES = { '.', '.', '-' };
	
	/* Dados para a formatação da Chave de Acesso */

	private static final int[] CHAVE_ACESSO_NUM_SEPARADORES = { 3, 7, 11, 15, 19, 23, 27, 31, 35, 39 };
	private static final char[] CHAVE_ACESSO_CHAR_SEPARADORES = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
	
	@Override
	public void afterReportInit() throws JRScriptletException {
		super.afterReportInit();
		
		logger.error("afterReportInit");
		
		formatarChaveDeAcesso();
		
		formatarFaturaEDuplicata();
		//formatarFaturaEDuplicataAuriVerde();
        
	}
	
	/**
	 * É executado no início de cada página.
	 */
	@Override
	public void afterPageInit() throws JRScriptletException {
		super.afterPageInit();
		
		logger.error("afterPageInit");
		
		formatarChaveDeAcesso();
		
		// formatarFaturaEDuplicata();
		
	}
	
	/**
	 * Tem por objetivo formatar o documento.
	 * 
	 * @param document
	 *            Documento (CPF, CNPJ).
	 * @return Documento formatado
	 */
	public String formatDocument(String document) {
		if (document == null || document.length() == 0) {
			return "";
		}
		
		if (document.length() == CNPJ_LENGTH) {
			// CNPJ
			char[] chars = document.toCharArray();
			return formatar(CNPJ_NUM_SEPARADORES, CNPJ_CHAR_SEPARADORES, chars);
		} else if (document.length() == 11) {
			// CPF
			char[] chars = document.toCharArray();
			return formatar(CPF_NUM_SEPARADORES, CPF_CHAR_SEPARADORES, chars);
		} else {
			return document;
		}
	}
	
	public void formatarFaturaEDuplicataLabCat() throws JRScriptletException 
	{
		org.apache.xerces.dom.DeferredElementImpl obj = (org.apache.xerces.dom.DeferredElementImpl) getFieldValue("cobr");
        if(obj != null)
        {
	    	org.w3c.dom.NodeList nl = obj.getChildNodes();
	    	
	    	int dup = 1;
	    	for (int i=0;((i < nl.getLength()) && (dup < 15));i++) {
	    		org.w3c.dom.Node node = nl.item(i);
	
	    		if ("dup".equals(node.getNodeName())) {
	    			formatarDuplicata(node,dup);
	    			dup++;
	    		}
	    	}
        }
	}
	
	public String getBcSTICMS(Object a)
	{
		Node campo = (Node) a;
		Node icmsXX = campo.getFirstChild();
		if ("#text".equals(icmsXX.getNodeName())) {
			icmsXX = icmsXX.getNextSibling();
		}
		if ("ICMS00".equals(icmsXX.getNodeName()) || "ICMS20".equals(icmsXX.getNodeName()) || "ICMS40".equals(icmsXX.getNodeName()) || "ICMS51".equals(icmsXX.getNodeName()) || "ICMS60".equals(icmsXX.getNodeName())) {
			return "0.00";
		}
		String vBCST = getNodeValue(icmsXX.getChildNodes(), "vBCST");
		if (NULO.equals(vBCST)) {
			return "0.00";
		}
		return vBCST;
	}
	
	public String getICMSST(Object a)
	{
		Node campo = (Node) a;
		Node icmsXX = campo.getFirstChild();
		if ("#text".equals(icmsXX.getNodeName())) {
			icmsXX = icmsXX.getNextSibling();
		}
		if ("ICMS00".equals(icmsXX.getNodeName()) || "ICMS20".equals(icmsXX.getNodeName()) || "ICMS40".equals(icmsXX.getNodeName()) || "ICMS51".equals(icmsXX.getNodeName()) || "ICMS60".equals(icmsXX.getNodeName())) {
			return "0.00";
		}
		String vICMSST = getNodeValue(icmsXX.getChildNodes(), "vICMSST");
		if (NULO.equals(vICMSST)) {
			return "0.00";
		}
		return vICMSST;
	}
	
	public String getBcICMS(Object a) {
		
		Node campo = (Node) a;
		Node icmsXX = campo.getFirstChild();
		if ("#text".equals(icmsXX.getNodeName())) {
			icmsXX = icmsXX.getNextSibling();
		}
		if ("ICMS40".equals(icmsXX.getNodeName()) || "ICMS51".equals(icmsXX.getNodeName())) {
			return "0.00";
		}
		String vBC = getNodeValue(icmsXX.getChildNodes(), "vBC");
		if (NULO.equals(vBC)) {
			return "0.00";
		}
		return vBC;
	}
	
	public String getDescProd(Object xProd,Object infAdProd)
	{
		String infAdProdString = "";
		String xProdString = String.valueOf(xProd);
		xProdString = xProdString.replaceAll("\n", "");
		xProdString = xProdString.replaceAll("\t", "");
		if(infAdProd != null)
		{
			infAdProdString = String.valueOf(infAdProd);
			if(infAdProdString.length() > 0)
			{
				infAdProdString = infAdProdString.replaceAll("\n", "");
				infAdProdString = infAdProdString.replaceAll("\t", "");
				String ret = xProdString + " (Inf. ad. Prod: " + infAdProdString + ")";
//				while(ret.length() > 80 && ret.length() <= 85)
//				{
//					ret += "_";
//				}
				return ret;
			}
		}
		return xProdString;
	}
	
	public String getVICMS(Object a) {
		Node campo = (Node) a;
		Node icmsXX = campo.getFirstChild();
		if ("#text".equals(icmsXX.getNodeName())) {
			icmsXX = icmsXX.getNextSibling();
		}
		if ("ICMS40".equals(icmsXX.getNodeName())) {
			return "0.00";
		}
		String vBC = getNodeValue(icmsXX.getChildNodes(), "vICMS");
		if (NULO.equals(vBC)) {
			return "0.00";
		}
		return vBC;
	}
	
	public java.lang.Double getValIPI(Object a) {
		// org.apache.xerces.dom.DeferredElementImpl imposto =
		// (org.apache.xerces.dom.DeferredElementImpl) a;
		Node imposto = (Node) a;
		Node ipi = getNode(imposto.getChildNodes(), "IPI");
		if (ipi == null) {
			return ZERO;
		}
		Node ipiTrib = getNode(ipi.getChildNodes(), "IPITrib");
		Node ipiNT = getNode(ipi.getChildNodes(), "IPINT");
		if (ipiTrib != null) {
			// IPITrib
			Node vBC = getNode(ipiTrib.getChildNodes(), "vIPI");
			if (vBC == null) {
				return ZERO;
			}
			String vl = vBC.getFirstChild().getNodeValue();
			return Double.valueOf(vl);
		} else if (ipiNT != null) {
			// IPINT
			return ZERO;
		} else {
			return ZERO;
		}
	}
	
	public String getVtotal(Object vUnCom,Object qCom,Object vProd)
	{
		Double vUnComD = Double.valueOf(vUnCom.toString());
		Double qComD = Double.valueOf(qCom.toString());
		Double vProdD = Double.valueOf(vProd.toString());
		if(qComD == 0)
		{
			return new java.text.DecimalFormat("#,##0.00").format(vProdD);
		}
		else
		{
			return new java.text.DecimalFormat("#,##0.00").format(vUnComD * qComD);
		}
	}
	
	public java.lang.Double getPICMS(Object a) {
		Node campo = (Node) a;
		Node icmsXX = campo.getFirstChild();
		if ("#text".equals(icmsXX.getNodeName())) {
			icmsXX = icmsXX.getNextSibling();
		}
		
		if ("ICMS40".equals(icmsXX.getNodeName())) {
			return ZERO;
		}
		
		String vBC = getNodeValue(icmsXX.getChildNodes(), "pICMS");
		if (vBC == null) {
			return ZERO;
		}
		if (NULO.equals(vBC)) {
			return ZERO;
		}
		return Double.valueOf(vBC);
	}
	
	/*
	 * ################################################################### Métodos
	 * Privados ###################################################################
	 */

	/**
	 * Formata os dados da chave de acesso para o formato exigido no manual do
	 * contribuinte.
	 * 
	 * @throws JRScriptletException
	 */
	private void formatarChaveDeAcesso() throws JRScriptletException {
		java.lang.String ch = ((java.lang.String) getFieldValue("Id"));
		if (ch != null) {
			ch = ch.substring(3);
			
			char[] chars = ch.toCharArray();
			
			setVariableValue("CHAVE_ACESSO", formatar(CHAVE_ACESSO_NUM_SEPARADORES, CHAVE_ACESSO_CHAR_SEPARADORES, chars));
		}
	}
	private void formatarFaturaEDuplicataAuriVerde() throws JRScriptletException 
	{
		Node obj = (Node) getFieldValue("cobr");
		
		if (obj != null) {
			NodeList nl = obj.getChildNodes();
			
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				
				if ("fat".equals(node.getNodeName())) {
					setFaturaAuriVerde(node,i);
				}
				
				if ("dup".equals(node.getNodeName())) {
					setDuplicataAuriVerde(node,i);
				}
			}
		}
	}
	/**
	 * Formata os dados em relação a fatura e duplicata.
	 * 
	 * @throws JRScriptletException
	 */
	private void formatarFaturaEDuplicata() throws JRScriptletException {
		Node obj = (Node) getFieldValue("cobr");
		
		if (obj != null) {
			NodeList nl = obj.getChildNodes();
			
			StringBuffer sbCobr = new StringBuffer();
			
			String comma = "";
			
			boolean hasFat = false;
			
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				
				if ("fat".equals(node.getNodeName())) {
					String s = formatarFatura(node);
					if (s != null) {
						sbCobr.append(comma).append(s);
						comma = "  -  ";
						hasFat = true;
					}
				}
				
				if ("dup".equals(node.getNodeName())) {
					String s = formatarDuplicata(node);
					if (s != null) {
						if (hasFat) {
							comma = "\n";
							hasFat = false;
						}
						sbCobr.append(comma).append(s);
						comma = "   ||   ";
					}
				}
			}
			
			setVariableValue("FATURA_E_DUPLICATA", sbCobr.toString());
		}
		
	}
	
	private String getNodeValue(NodeList nl, String tag) {
		Node ok = null;
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (tag.equals(node.getNodeName())) {
				ok = node;
				break;
			}
		}
		if (ok != null) {
			return ok.getFirstChild().getNodeValue();
		} else {
			return NULO;
		}
	}
	
	private Node getNode(NodeList nl, String tag) {
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (tag.equals(node.getNodeName())) {
				return node;
			}
		}
		return null;
	}
	
	private String formatar(int[] iSeparadores, char[] cSeparadores, char[] chars) {
		java.lang.StringBuffer sb = new java.lang.StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			int a = java.util.Arrays.binarySearch(iSeparadores, i);
			sb.append(c);
			if (a >= 0) {
				sb.append(cSeparadores[a]);
			}
		}
		return sb.toString();
	}
	
	private String getDtFormatada(String dt) {
		DecimalFormat d1 = new DecimalFormat("00");
		int ano = Integer.valueOf(dt.substring(2, 4));
		int mes = Integer.valueOf(dt.substring(5, 7));
		int dia = Integer.valueOf(dt.substring(8, 10));
		StringBuffer sb = new StringBuffer();
		sb.append(d1.format(dia)).append("/").append(d1.format(mes)).append("/").append(d1.format(ano));
		return sb.toString();
	}
	private void setFaturaAuriVerde(Node node,int i)
	{
		DecimalFormat d2 = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
		DecimalFormat d4 = new DecimalFormat("#,###.00");
		
		StringBuffer sb = new StringBuffer();
		
		String nFat = getNodeValue(node.getChildNodes(), "nFat");
		double vOrig = 0;
		double vDesc = 0;
		double vLiq = 0;
		try {
			if(getNodeValue(node.getChildNodes(), "vDup").equalsIgnoreCase("NULO"))
				vOrig = 0;
			else
				vOrig = d2.parse(getNodeValue(node.getChildNodes(), "vDup")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		try {
			if(getNodeValue(node.getChildNodes(), "vDesc").equalsIgnoreCase("NULO"))
				vDesc = 0;
			else
				vDesc = d2.parse(getNodeValue(node.getChildNodes(), "vDesc")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		try {
			if(getNodeValue(node.getChildNodes(), "vLiq").equalsIgnoreCase("NULO"))
				vLiq = 0;
			else				
				vLiq = d2.parse(getNodeValue(node.getChildNodes(), "vLiq")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		String comma = "";
		if (!NULO.equals(nFat)) {
			sb.append("Num. Fat.: ").append(nFat);
			comma = " - ";
		}
		
		if (vOrig > 0) {
			sb.append(comma).append("Valor: ").append(d4.format(vOrig));
			comma = " - ";
		}
		
		if (vDesc > 0) {
			sb.append(comma).append("Vl. Desc.: ").append(d4.format(vDesc));
			comma = " - ";
		}
		
		if (vLiq > 0) {
			sb.append(comma).append("Vl. Liq.: ").append(d4.format(vLiq));
		}
	}
	private String formatarFatura(Node node) {
		DecimalFormat d2 = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
		DecimalFormat d4 = new DecimalFormat("#,###.00");
		
		StringBuffer sb = new StringBuffer();
		
		String nFat = getNodeValue(node.getChildNodes(), "nFat");
		double vOrig = 0;
		double vDesc = 0;
		double vLiq = 0;
		try {
			if(getNodeValue(node.getChildNodes(), "vDup").equalsIgnoreCase("NULO"))
				vOrig = 0;
			else
				vOrig = d2.parse(getNodeValue(node.getChildNodes(), "vDup")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		try {
			if(getNodeValue(node.getChildNodes(), "vDesc").equalsIgnoreCase("NULO"))
				vDesc = 0;
			else
				vDesc = d2.parse(getNodeValue(node.getChildNodes(), "vDesc")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		try {
			if(getNodeValue(node.getChildNodes(), "vLiq").equalsIgnoreCase("NULO"))
				vLiq = 0;
			else				
				vLiq = d2.parse(getNodeValue(node.getChildNodes(), "vLiq")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		String comma = "";
		if (!NULO.equals(nFat)) {
			sb.append("Num. Fat.: ").append(nFat);
			comma = " - ";
		}
		
		if (vOrig > 0) {
			sb.append(comma).append("Valor: ").append(d4.format(vOrig));
			comma = " - ";
		}
		
		if (vDesc > 0) {
			sb.append(comma).append("Vl. Desc.: ").append(d4.format(vDesc));
			comma = " - ";
		}
		
		if (vLiq > 0) {
			sb.append(comma).append("Vl. Liq.: ").append(d4.format(vLiq));
		}
		
		return sb.toString();
	}
	private void setDuplicataAuriVerde(Node node,int i) throws JRScriptletException
	{
		DecimalFormat d2 = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
		DecimalFormat d4 = new DecimalFormat("#,###.00");
		
		String nDup = getNodeValue(node.getChildNodes(), "nDup");
		String dVenc = getNodeValue(node.getChildNodes(), "dVenc");
		double vDup = 0;
		try {
			vDup = d2.parse(getNodeValue(node.getChildNodes(), "vDup")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		if (!NULO.equals(nDup)) {
			setVariableValue("NUM_DUP_0_"+i, nDup);
		}
		
		if (!NULO.equals(dVenc)) {
			setVariableValue("DT_VENC_DUP_0_"+i, getDtFormatada(dVenc));
		}
		
		if (!NULO.equals(vDup)) {
			setVariableValue("VALOR_DUP_0_"+i, d4.format(vDup));
		}
	}
	private String formatarDuplicata(Node node) {
		DecimalFormat d2 = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
		DecimalFormat d4 = new DecimalFormat("#,###.00");
		
		StringBuffer sb = new StringBuffer();
		String nDup = getNodeValue(node.getChildNodes(), "nDup");
		String dVenc = getNodeValue(node.getChildNodes(), "dVenc");
		double vDup = 0;
		try {
			vDup = d2.parse(getNodeValue(node.getChildNodes(), "vDup")).doubleValue();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		String comma = "";
		if (!NULO.equals(nDup)) {
			sb.append("Num. Dup.: ").append(nDup);
			comma = " - ";
		}
		
		if (!NULO.equals(dVenc)) {
			sb.append(comma).append("Dt. Venc.: ").append(getDtFormatada(dVenc));
			comma = " - ";
		}
		
		if (!NULO.equals(vDup)) {
			sb.append(comma).append("Valor: ").append(d4.format(vDup));
		}
		
		return sb.toString();
	}
	public void formatarDuplicata(org.w3c.dom.Node node, int dup) {
		DecimalFormat d1 = new DecimalFormat("00");
		DecimalFormat d2 = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.US));
		DecimalFormat d4 = new DecimalFormat("#,###.00");

		String nDup = getNodeValue(node.getChildNodes(),"nDup");
		String dVenc = getNodeValue(node.getChildNodes(),"dVenc");
		double vDup = 0;
		try {
			vDup = d2.parse(getNodeValue(node.getChildNodes(),"vDup")).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			
			String contador = d1.format(dup);
			setVariableValue("VALOR_" + contador,d4.format(vDup));
			setVariableValue("VENC_" + contador,getDtFormatada(dVenc));
	
			int[] iSeparadores = { 5 };
			char[] cSeparadores = { '/' };
			char[] chars = nDup.toCharArray();
			setVariableValue("DUPL_" + contador,formatar(iSeparadores, cSeparadores, chars));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
