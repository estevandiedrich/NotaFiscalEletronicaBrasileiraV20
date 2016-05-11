package br.com.hs.nfe.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.linear.code128.Code128Barcode;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.xpath.XPathUtil;

public class DanfeBuilder {
	/**
	 * Modelo padr�o para DANFE no formato retrato, modelo 55.
	 */
	public static final String DANFE_055_RETRATO = "danfe-055-retrato.jasper";
	/**
	 * Modelo padr�o para DANFE no formato paisagem, modelo 55.
	 */
	public static final String DANFE_055_RETRATO_FSDA = "danfe-contingencia-055-retrato.jasper";
	
	public static final String DANFE_055_PAISAGEM = "danfe-055-paisagem.jasper";	
	
	public static final String DANFE_055_PAISAGEM_50_DUP = "danfe-055-paisagem-50-dup.jasper";
	
	private static final Logger logger = Logger.getLogger("DanfeBuilder");
	
	public static void main(String... m)
	{
		try
		{
			File file = new File("C:/filas/14589140000151/enviNFe/xmlProcessados/NFe42120614589140000151550010000000371086416793.xml");
			FileInputStream fis = new FileInputStream(file);
			byte[] bNFe = IOUtils.toByteArray(fis);
			File file2 = new File("C:/dev.estevan/workspace-java/NotaFiscalEletronicaBrasileiraV20/conf/branco.gif");
			FileInputStream fis2 = new FileInputStream(file2);
			File file3 = new File("C:/dev.estevan/workspace-java/NotaFiscalEletronicaBrasileiraV20/resources/"+DanfeBuilder.DANFE_055_RETRATO);
			FileInputStream fis3 = new FileInputStream(file3);
			byte[] danfe = new DanfeBuilder().montaDanfe(bNFe, fis2, fis3, "GURU EDITORA TECNICA LTDA ME");
			FileOutputStream fos = new FileOutputStream("C:/filas/14589140000151/enviNFe/danfe/NFe42120614589140000151550010000000371086416793.pdf");
			fos.write(danfe);
			fos.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public synchronized byte[] montaDanfe(byte bNfe[], InputStream logo,InputStream isLayout,String razaoSocial) throws Exception
	{
		return montaDanfe(bNfe,logo,isLayout,razaoSocial,"");
	}
	public synchronized byte[] montaDanfe(byte bNfe[], InputStream logo,InputStream isLayout,String razaoSocial,String chaveDados) throws Exception
    {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		
		//InputStream isLayout = Thread.currentThread().getContextClassLoader().getResourceAsStream(formato);
		//File file = new File("../resources/"+formato);
		//InputStream isLayout = new FileInputStream(file);
		jasperReport = (JasperReport) JRLoader.loadObject(isLayout);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String id = XPathUtil.solveXPath(new ByteArrayInputStream(bNfe), "//:nfeProc/:NFe/:infNFe/@Id");
		id = id.substring(3);
		Barcode barcode = new Code128Barcode(id, Code128Barcode.C);
		barcode.setBarHeight(68);
		barcode.setDrawingText(false);
		BarcodeImageHandler.writeJPEG(barcode, bos);
		ByteArrayInputStream barCodeImage = new ByteArrayInputStream(bos.toByteArray());
		ByteArrayInputStream barCodeImageAdicional = null;
		if(!chaveDados.equalsIgnoreCase(""))
		{
			bos = new ByteArrayOutputStream();
			Barcode barcodeAdicional = new Code128Barcode(chaveDados, Code128Barcode.C);
			barcode.setBarHeight(68);
			barcode.setDrawingText(false);
			BarcodeImageHandler.writeJPEG(barcodeAdicional, bos);
			barCodeImageAdicional = new ByteArrayInputStream(bos.toByteArray());
			IOUtils.closeQuietly(bos);
		}
		JRXmlDataSource jrDataSource = new JRXmlDataSource(new ByteArrayInputStream(bNfe), "/nfeProc/NFe/infNFe/det");
		Map<Object, Object> param = new HashMap<Object, Object>();
		if (logo != null) {
			param.put("LOGO", logo);
		}
		if (barCodeImage != null) {
			param.put("BARCODE", barCodeImage);
		}
		if(barCodeImageAdicional != null)
		{
			param.put("BARCODE_ADICIONAL", barCodeImageAdicional);
			param.put("CHAVE_ACESSO_DADOS",chaveDados);
		}
		String transQtdVol = XPathUtil.solveXPath(new ByteArrayInputStream(bNfe), "//:nfeProc/:NFe/:infNFe/:transp/:vol/:qVol");
		String transPesoBruto = XPathUtil.solveXPath(new ByteArrayInputStream(bNfe), "//:nfeProc/:NFe/:infNFe/:transp/:vol/:pesoB");
		String transPesoLiq = XPathUtil.solveXPath(new ByteArrayInputStream(bNfe), "//:nfeProc/:NFe/:infNFe/:transp/:vol/:pesoL");
		param.put("TRANS_QTD_VOL",transQtdVol);
		param.put("TRANS_PESO_BRUTO",transPesoBruto);
		param.put("TRANS_PESO_LIQ",transPesoLiq);
		param.put("RAZAO_SOCIAL", razaoSocial);
		//param.put("TRANS_MARCA", daNfe.marcaVolumes());
		//param.put("TRANS_ESPECIE", daNfe.especieVolumes());
		//param.put("TRANS_NUMERACAO", daNfe.numeracaoVolumes());
		logger.info("Raz�o social : "+razaoSocial);
		jasperPrint = JasperFillManager.fillReport(jasperReport, param, jrDataSource);
		IOUtils.closeQuietly(logo);
		IOUtils.closeQuietly(isLayout);
		IOUtils.closeQuietly(barCodeImage);
		IOUtils.closeQuietly(barCodeImageAdicional);
		// Gera a sa�da em PDF.
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);
		return output.toByteArray();
    }
}
