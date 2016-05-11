package br.com.hs.nfe.zip;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import br.com.hs.nfe.vo.ZipChecksumVO;

public class Zip {
   static final int BUFFER = 2048;
   private static final Logger logger = Logger.getLogger("Zip");
   public static synchronized ZipChecksumVO compacta (String xml,String xmlName,String zipPath) 
   {
	  ByteArrayOutputStream temp = new ByteArrayOutputStream();
	  ZipChecksumVO zipChecksumVO = new ZipChecksumVO();
	  try 
	  {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(zipPath);
			CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
			//out.setMethod(ZipOutputStream.DEFLATED);
			byte data[] = new byte[BUFFER];
			// get a list of files from current directory
	
			//logger.info("Adding: "+xmlPath);
			//FileInputStream fi = new FileInputStream(xmlPath);
			InputStream is = IOUtils.toInputStream(xml);
			origin = new BufferedInputStream(is, BUFFER);
			ZipEntry entry = new ZipEntry(xmlName);
			out.putNextEntry(entry);
			int count;
			while((count = origin.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
				temp.write(data);
			}
			origin.close();
			out.close();
			logger.info("checksum: "+checksum.getChecksum().getValue());
			zipChecksumVO.setChecksum(Long.toString(checksum.getChecksum().getValue()));
	  } 
	  catch(Exception e) {
		  	logger.error(e);
	  }
	  byte[] ret = temp.toByteArray();
	  IOUtils.closeQuietly(temp);
	  zipChecksumVO.setZip(ret);
	  return zipChecksumVO;
   }
}