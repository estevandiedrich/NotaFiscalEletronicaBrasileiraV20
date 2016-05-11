package br.com.hs.nfe.email;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

import br.com.hs.nfe.zip.DescompactaAnexoEmail;

public class ReceberEmail {
	private static final Logger logger = Logger.getLogger("ReceberEmail");
	private static ArrayList<InputStream> ret = null;
	public static synchronized InputStream[] receber(String servidor,String porta,String usuario,String senha)
	{
		ret = new ArrayList<InputStream>();
		Properties props = new Properties();
		props.put("mail.pop3.host", servidor);
		props.put("mail.pop3.port", porta);
		props.put("mail.store.protocol", "pop3");
		//props.put("mail.pop3.auth", "true");
		props.put("mail.pop3.ssl.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.pop3.starttls.enable","true");

	    // Get session
	    Session session = Session.getInstance(props, null);

	    // Get the store
	    Store store = null;
		try {
			store = session.getStore("pop3");
			store.connect(usuario, senha);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("MessagingException",e);
		} catch (NullPointerException npe) {
			logger.error("NullPointerException",npe);
		}

	    // Get folder
	    Folder folder = null;
		try {
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
		}
	    catch (MessagingException e) {
	    	// TODO Auto-generated catch block
	    	logger.error("MessagingException",e);
	    }

	    // Get directory
	    Message message[] = null;
		try {
			message = folder.getMessages();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("MessagingException",e);
		}
	    for (int i=0, n=message.length; i<n; i++) {
	       try 
	       {
				logger.info(i + ": " + message[i].getFrom()[0] + "\t" + message[i].getSubject());
	
		        Object content = message[i].getContent();
		        if (content instanceof Multipart) {
		          handleMultipart((Multipart)content);
		        } else {
		          handlePart(message[i]);
		        }
	        } catch (MessagingException e) {
	    	// TODO Auto-generated catch block
	        	logger.error("MessagingException",e);
	        } catch (IOException ioe)
	        {
	        	logger.error("IOException",ioe);
	        }
	    }

	    // Close connection 
	    try {
			folder.close(false);
			store.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("MessagingException",e);
		}
	    
	    return ret.toArray(new InputStream[0]);
	  }
	  public static void handleMultipart(Multipart multipart) 
	      throws MessagingException, IOException {
	    for (int i=0, n=multipart.getCount(); i<n; i++) {
	      handlePart(multipart.getBodyPart(i));
	    }
	  }
	  public static void handlePart(Part part) 
	      throws MessagingException, IOException {
	    String disposition = part.getDisposition();
	    String contentType = part.getContentType();
	    if (disposition == null) { // When just body
	      logger.info("Null: "  + contentType);
	      // Check if plain
	      if ((contentType.length() >= 10) && 
	          (contentType.toLowerCase().substring(
	           0, 10).equals("text/plain"))) {
	        //part.writeTo(System.out);
	      } else { // Don't think this will happen
	        logger.info("Other body: " + contentType);
	        //part.writeTo(System.out);
	      }
	    } else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
	      logger.info("Attachment: " + part.getFileName() + " : " + contentType);
	      //saveFile(part.getFileName(), part.getInputStream());
	      if(part.getFileName().contains(".zip")||part.getFileName().contains(".rar"))
	      {
	    	  for(InputStream is:DescompactaAnexoEmail.descompactaZip(part.getInputStream()))
	    	  {
	    		  ret.add(is);
	    	  }
	      }
	      else
	      {
	    	  ret.add(part.getInputStream());
	      }
	    } else if (disposition.equalsIgnoreCase(Part.INLINE)) {
	      logger.info("Inline: " + part.getFileName() + " : " + contentType);
	      //saveFile(part.getFileName(), part.getInputStream());
	      ret.add(part.getInputStream());
	    } else {  // Should never happen
	      logger.info("Other: " + disposition);
	    }
	  }
	  public static void saveFile(String filename,
	      InputStream input) throws IOException {
	    if (filename == null) {
	      filename = File.createTempFile("xxxxxxxx", ".out").getName();
	    }
	    // Do no overwrite existing file
	    File file = new File(filename);
	    for (int i=0; file.exists(); i++) {
	      file = new File(filename+i);
	    }
	    FileOutputStream fos = new FileOutputStream(file);
	    BufferedOutputStream bos = new BufferedOutputStream(fos);

	    BufferedInputStream bis = new BufferedInputStream(input);
	    int aByte;
	    while ((aByte = bis.read()) != -1) {
	      bos.write(aByte);
	    }
	    bos.flush();
	    bos.close();
	    bis.close();
	  }
}
