package br.com.hs.nfe.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import br.com.hs.nfe.entity.Estabelecimento;

public class EnviarEmail {
	static String msgText = "This is a message body.\nHere's the second line.";
	private static final Logger logger = Logger.getLogger("EnviarEmail");
	private static Estabelecimento configVO;
	/**
	 * @param args
	 */
//	public static void main(String ...args)
//	{
//		EnviarEmail.enviar(msgText,"c:\\temp\\z.pdf");
//	}
	public static synchronized void enviar(String assunto,String mensagem,Estabelecimento config,String[] recipients)
	{
		enviar(assunto,mensagem,"","",config,recipients);
	}
	public static synchronized void enviar(String assunto,String mensagem,String xmlAttachment,String pdfAttachment,Estabelecimento config,String[] recipients) {
		
		configVO = config;
		Properties props = new Properties();
		props.put("mail.smtp.host", config.getServidorEmail());
		props.put("mail.smtp.port", config.getPortaEmail());
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable","true");
	    
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.fallback", "false");  

		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Authenticator auth = new SMTPAuthenticator();

		Session session = Session.getInstance(props, auth);
		//session.getTransport().
		session.setDebug(true);
		
		
		try {
		    // create a message
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress(config.getEmailDe()));
		    InternetAddress[] address = null;
		    ArrayList<InternetAddress> addressList = new ArrayList<InternetAddress>();
		    if(recipients != null)
		    {
			    for(String recipient:recipients)
		    	{
			    	if(!recipient.equalsIgnoreCase(""))
			    	{
			    		addressList.add(new InternetAddress(recipient));
			    	}
		    	}
		    }
		    if(config.getEmailPara() != null && !config.getEmailPara().equalsIgnoreCase(""))
		    {
		    	if(config.getEmailPara().contains(";"))
		    	{
		    		String[] emailParaArray = config.getEmailPara().split(";");
		    		for(String emailPara:emailParaArray)
		    		{
		    			if(!emailPara.equalsIgnoreCase(""))
		    			{
		    				addressList.add(new InternetAddress(emailPara));
		    			}
		    		}
		    	}
		    	else
		    	{
		    		addressList.add(new InternetAddress(config.getEmailPara()));
		    	}
		    }
		    address = addressList.toArray(new InternetAddress[0]);
		    msg.setRecipients(Message.RecipientType.TO, address);
		    msg.setSubject(assunto);
		    msg.setSentDate(new Date());
		    // If the desired charset is known, you can use
		    // setText(text, charset)
		    msg.setText(mensagem);
		    // create the message part 
		    MimeBodyPart messageBodyPart = new MimeBodyPart();

		    //fill message
		    messageBodyPart.setText(config.getCorpoEmail());

		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);

		    // Part two is attachment
		    DataSource source = null;
		    if(!pdfAttachment.equalsIgnoreCase(""))
		    {
			    messageBodyPart = new MimeBodyPart();
			    source = 
			      new FileDataSource(pdfAttachment);
			    messageBodyPart.setDataHandler(
			      new DataHandler(source));
			    messageBodyPart.setFileName(pdfAttachment);
			    multipart.addBodyPart(messageBodyPart);
		    }
		    // Part two is attachment
		    if(!xmlAttachment.equalsIgnoreCase(""))
		    {
			    messageBodyPart = new MimeBodyPart();
			    source = 
			      new FileDataSource(xmlAttachment);
			    messageBodyPart.setDataHandler(
			      new DataHandler(source));
			    messageBodyPart.setFileName(xmlAttachment);
			    multipart.addBodyPart(messageBodyPart);
		    }
		    // Put parts in message
		    msg.setContent(multipart);

		    // Send the message
		    Transport.send(msg);
		} catch (MessagingException mex) {
		    logger.info("\n--Exception handling in msgsendsample.java");

		    mex.printStackTrace();
		    Exception ex = mex;
		    do {
			if (ex instanceof SendFailedException) {
			    SendFailedException sfex = (SendFailedException)ex;
			    Address[] invalid = sfex.getInvalidAddresses();
			    if (invalid != null) {
				logger.info("    ** Invalid Addresses");
				if (invalid != null) {
				    for (int i = 0; i < invalid.length; i++) 
				    	logger.info("         " + invalid[i]);
				}
			    }
			    Address[] validUnsent = sfex.getValidUnsentAddresses();
			    if (validUnsent != null) {
				logger.info("    ** ValidUnsent Addresses");
				if (validUnsent != null) {
				    for (int i = 0; i < validUnsent.length; i++) 
					logger.info("         "+validUnsent[i]);
				}
			    }
			    Address[] validSent = sfex.getValidSentAddresses();
			    if (validSent != null) {
				logger.info("    ** ValidSent Addresses");
				if (validSent != null) {
				    for (int i = 0; i < validSent.length; i++) 
					logger.info("         "+validSent[i]);
				}
			    }
			}
			if (ex instanceof MessagingException)
			    ex = ((MessagingException)ex).getNextException();
			else
			    ex = null;
		    } while (ex != null);
		}
	}
	
	public static class SMTPAuthenticator extends javax.mail.Authenticator {
	    public PasswordAuthentication getPasswordAuthentication() {
	       return new PasswordAuthentication(configVO.getUsuarioEmail(), configVO.getSenhaEmail());
	    }
	}
}

