package br.com.hs.nfe.print;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.log4j.Logger;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.encryption.AccessPermission;

public class ImprimirPDF {
	public ImprimirPDF()
	{

	}
	
	private static final Logger logger = Logger.getLogger("ImprimirPDF");
	private PrintService getPrinterByName(String nomeImpressora) {
		//PrintService[] impressoras = PrinterJob.lookupPrintServices();
		PrintService[] impressoras = PrintServiceLookup.lookupPrintServices(null, null);
		logger.info("Impressoras castradas:");
		for (PrintService imp : impressoras) {
			logger.info("Impressora: " + imp.getName());
			if (imp.getName().equalsIgnoreCase(nomeImpressora)) {
				return imp;
			}
		}
		return null;
	}
	public void imprimir(byte[] msg,String nomeImpressora,int copias,boolean impressaoSilenciosa) throws Exception {
		PrintService ps = null;
		ps = getPrinterByName(nomeImpressora);
		if (ps == null) {
			logger.error("N�o realizou impress�o pois n�o foi configurada uma impressora");
		}
		logger.info("Preparando para impress�o");
		
		logger.info("Impressora: [" + ps.getName() + "]");
		PDDocument document;
		
		document = null;
		document = PDDocument.load(new ByteArrayInputStream(msg));
		/*
		 * Caso o arquivo precise de senha, deve ativar o if abaixo
		 */
//		 if (document.isEncrypted()) {
//		 document.decrypt(password);
//		 }
		AccessPermission currentPermissions = document.getCurrentAccessPermission();
		if (!currentPermissions.canPrint()) {
			throw new PrinterException("You do not have permission to print this document.");
		}
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPageable(document);
		printJob.setPrintService(ps);
		printJob.setCopies(copias);
		printJob.setJobName("NFe HS Print Job");
		if (impressaoSilenciosa) {
				printJob.print();
		} else if (printJob.printDialog())
		{
				printJob.print();
		}
		
		if (document != null)
			document.close();
	}
	public static void main(String[] args) throws Exception {
		String arquivo = "C:\\filas\\05537225000103\\enviNFe\\danfe\\NFe42110905537225000103550010000108395200640358.pdf";
		
		InputStream is = new FileInputStream(new File(arquivo));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		int len;
		while ((len = is.read(buffer)) >= 0) {
			baos.write(buffer, 0, len);
		}
		is.close();
		baos.close();
		byte msg[] = baos.toByteArray();
		
		new ImprimirPDF().imprimir(msg,"PDFCreator",1,true);
		logger.info("Terminou");
		
	}
}
