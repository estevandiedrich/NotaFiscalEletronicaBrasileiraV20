package br.com.hs.nfe.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

public class DescompactaAnexoEmail {
	private static final Logger logger = Logger.getLogger("DescompactaAnexoEmail");
	public static synchronized InputStream[] descompactaZip(InputStream is)
	{
		List<InputStream> ret = new ArrayList<InputStream>();
		try {
	         final int BUFFER = 2048;
	         ByteArrayOutputStream dest = null;
	         CheckedInputStream checksum = new CheckedInputStream(is, new Adler32());
	         ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	        	logger.info("Extracting: " +entry);
	            int count;
	            byte data[] = new byte[BUFFER];
	            // write the files to the disk
	            dest = new ByteArrayOutputStream();
	            while ((count = zis.read(data, 0, BUFFER)) != -1) {
	               dest.write(data, 0, count);
	            }
	            ret.add(new ByteArrayInputStream(dest.toByteArray()));
	            dest.flush();
	            dest.close();
	         }
	         zis.close();
	         logger.info("Checksum: "+checksum.getChecksum().getValue());
	      } catch(Exception e) {
	         logger.error("Erro ao descompactar anexo",e);
	      }
	      return ret.toArray(new InputStream[0]);
	}
}
