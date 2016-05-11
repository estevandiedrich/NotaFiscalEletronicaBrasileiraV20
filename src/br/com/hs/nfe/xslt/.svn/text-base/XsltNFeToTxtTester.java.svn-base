package br.com.hs.nfe.xslt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

public class XsltNFeToTxtTester {
	
	public static void main(String[] args) throws Exception {
		XsltNFeToTxt xlstNFeToTxt = new XsltNFeToTxt("nfeentrada.to.txt.xsl");
		
//		String a = null;
//		a = "Propagacao_100011_894_wdhc706you_entrada.xml";
//		System.out.println(t.getAcao(IOUtils.getBytesFromInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(a))));
//		System.out.println(t.getNomeXSL(t.getAcao(IOUtils.getBytesFromInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(a)))));
//		
//		a = "Propagacao_100018_894_3ihhm8yfot_entrada.xml";
//		System.out.println(t.getAcao(IOUtils.getBytesFromInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(a))));
		
		byte[] b = null;
		//File nfeProc = new File("C:\\filas\\entrada\\xmlValidos\\NFe42110905537225000286550010000037914200079090.xml");
		File nfeProc = new File("C:\\filas\\entrada\\xmlValidos\\NFe12000000000000000000000000000000000000000000.xml");
		FileInputStream fis = new FileInputStream(nfeProc);
		b = IOUtils.toByteArray(fis);
		//b = "nota671serie8.xml";
		byte[] resNfe = xlstNFeToTxt.transformar(b);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(resNfe);
		System.out.println(baos.toString());

	}
}
