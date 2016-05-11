package br.com.hs.nfe.zip;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class UnZip {
	public static void main(String... args)
	{
		UnZip.descompacta("C:\\filas\\05537225000286\\enviNFe\\xmlCompactados\\NFe42111105537225000286550010000041461200083726.zip");
	}
	private static final Logger logger = Logger.getLogger("UnZip");
	public static synchronized byte[] descompacta(InputStream is)
	{
		byte[] ret = null;
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		try {
	         final int BUFFER = 2048;
	         BufferedOutputStream dest = null;
	         CheckedInputStream checksum = new CheckedInputStream(is, new Adler32());
	         ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	            logger.info("Extracting: " +entry);
	            int count;
	            byte data[] = new byte[BUFFER];
	            //FileOutputStream fos = new FileOutputStream(entry.getName());
	            dest = new BufferedOutputStream(temp,BUFFER);
	            while ((count = zis.read(data, 0, BUFFER)) != -1) {
	                dest.write(data, 0, count);
	             }
	            dest.flush();
	            dest.close();
	         }
	         zis.close();
	         logger.info("Checksum: "+checksum.getChecksum().getValue());
	      } catch(Exception e) {
	         logger.error(e);
	      }
	      ret = temp.toByteArray();
          IOUtils.closeQuietly(temp);
	      return ret;
	}
	public static synchronized byte[] descompacta(byte[] zip)
	{
		return descompacta(new ByteArrayInputStream(zip));
	}
	public static synchronized byte[] descompacta (String zip) 
	{
		try {
			return descompacta(new FileInputStream(zip));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return null;
		}
	}
}
