package br.com.hs.nfe.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class FileManager {
	private FileManager()
	{
		
	}
	private static FileManager me = null;
	private static final Logger logger = Logger.getLogger("FileManager");
	public static synchronized FileManager getInstance()
	{
		if(me == null)
		{
			me = new FileManager();
		}
		return me;
	}
	public synchronized boolean saveFile(String dir,File file)
	{
		File outputFile = new File(dir+File.separatorChar+file);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		logger.info("Salvando arquivo : "+outputFile.getAbsolutePath());
		try {
			outputFile.createNewFile();
			fos = new FileOutputStream(outputFile);
			fis = new FileInputStream(file);
			IOUtils.copy(fis, fos);
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(fos);
			logger.info("Arquivo salvo com sucesso : "+outputFile.getAbsolutePath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return true;
	}
	public synchronized boolean saveFile(String caminhoENome,String conteudo)
	{
		File outputFile = new File(caminhoENome);
		FileOutputStream fos = null;
		InputStream fis = new ByteArrayInputStream(conteudo.getBytes());
		logger.info("Salvando arquivo : "+outputFile.getAbsolutePath());
		try {
			outputFile.createNewFile();
			fos = new FileOutputStream(outputFile);
			//fis = new FileInputStream(file);
			IOUtils.copy(fis, fos);
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(fos);
			logger.info("Arquivo salvo com sucesso : "+outputFile.getAbsolutePath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return false;
		}
		return true;
	}
	public synchronized File[] getFiles(String dir,final String extensao)
	{
		logger.info("Recebendo arquivos "+extensao);
		java.io.File diretorio = new java.io.File(dir);
	    File[] arquivos = diretorio.listFiles(
		        new FileFilter()
		        {
		            public boolean accept(File dir) {
		                return dir.isFile() && (dir.getName().toLowerCase().trim().endsWith(extensao));
		        }
	        }
	    ); 
	    logger.info("Arquivos TXT recebidos");
	    return arquivos;
	}
	public synchronized File[] getTXTFiles(String dir)
	{
		logger.info("Recebendo arquivos TXT");
		java.io.File diretorio = new java.io.File(dir);
	    File[] arquivos = diretorio.listFiles(
		        new FileFilter()
		        {
		            public boolean accept(File dir) {
		                return dir.isFile() && (dir.getName().endsWith("txt") || dir.getName().endsWith("TXT"));
		        }
	        }
	    ); 
	    logger.info("Arquivos TXT recebidos");
	    return arquivos;
	}
	public synchronized File[] getXMLFiles(String dir)
	{
		logger.info("Recebendo arquivos XML");
		java.io.File diretorio = new java.io.File(dir);
	    File[] arquivos = diretorio.listFiles(
		        new FileFilter()
		        {
		            public boolean accept(File dir) {
		                return dir.isFile() && dir.getName().endsWith("xml");
		        }
	        }
	    ); 
	    logger.info("Arquivos XML recebidos");
	    return arquivos;
	}
	public synchronized File[] getZipFiles(String dir)
	{
		logger.info("Recebendo arquivos ZIP");
		java.io.File diretorio = new java.io.File(dir);
	    File[] arquivos = diretorio.listFiles(
		        new FileFilter()
		        {
		            public boolean accept(File dir) {
		                return dir.isFile() && dir.getName().endsWith("zip");
		        }
	        }
	    ); 
	    logger.info("Arquivos XML recebidos");
	    return arquivos;
	}
	public synchronized File[] getPdfFiles(String dir)
	{
		logger.info("Recebendo arquivos PDF");
		java.io.File diretorio = new java.io.File(dir);
	    File[] arquivos = diretorio.listFiles(
		        new FileFilter()
		        {
		            public boolean accept(File dir) {
		                return dir.isFile() && dir.getName().endsWith("pdf");
		        }
	        }
	    ); 
	    logger.info("Arquivos PDF recebidos");
	    return arquivos;
	}
}
