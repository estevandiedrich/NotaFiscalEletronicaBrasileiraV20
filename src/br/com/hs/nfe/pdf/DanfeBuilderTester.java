package br.com.hs.nfe.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class DanfeBuilderTester {
	public static void main(String[] args) throws Exception {
		//FileInputStream fis = new FileInputStream("NFe42110883158824000626550010000069601208496730-nfeProc.xml");
		InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream("634608299407812500_NFe42111283158824000111550010000732121207717508_procNFe.xml");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IOUtils.copy(fis, baos);
		InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("branco.gif");
		//File logo = new File("../conf/branco.gif");
		//InputStream is = new FileInputStream(logo);
		File file = new File("../resources/"+DanfeBuilder.DANFE_055_RETRATO);
		byte[] ba = new DanfeBuilder().montaDanfe(baos.toByteArray(),is,new FileInputStream(file),"Razao social");
		FileOutputStream fos = new FileOutputStream("c:/temp/t.pdf");
		fos.write(ba);
		fos.close();
	}
}
