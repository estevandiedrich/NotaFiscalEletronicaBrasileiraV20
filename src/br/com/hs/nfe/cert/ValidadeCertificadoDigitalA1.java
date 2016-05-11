package br.com.hs.nfe.cert;  
  
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.hs.nfe.entity.Estabelecimento;
  
public class ValidadeCertificadoDigitalA1 {  
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    private static final Logger logger = Logger.getLogger("ValidadeCertificadoDigitalA1");
    private Estabelecimento config = null;
    private X509Certificate cert = null;
    public ValidadeCertificadoDigitalA1(Estabelecimento config)
    {
    	this.config = config;
    	Certificate certificate = null;
        try {
        	if("A1".equalsIgnoreCase(config.getTipoCertificado()))
        	{
	            KeyStore keystore = KeyStore.getInstance("JKS");
	    		InputStream is = new FileInputStream(this.config.getJks());
	    		keystore.load(is, this.config.getJksPassword().toCharArray());
	    		is.close();
	              
	    		
	    		Key key = keystore.getKey(this.config.getPfxAlias(), this.config.getPfxPassword().toCharArray());
	    		if (key instanceof PrivateKey) {
	    			certificate = keystore.getCertificate(this.config.getPfxAlias());
	    			cert = (X509Certificate) certificate;    
	                
	    			logger.info(cert.getSubjectDN().getName());  
	    			logger.info("Válido a partir de..: " + dateFormat.format(cert.getNotBefore()));  
	    			logger.info("Válido até..........: " + dateFormat.format(cert.getNotAfter()));
	    		}	
        	}
        } catch (Exception e) {  
            logger.error(e);  
        } 
    }
    public Date verificaValidadeCertificadoDigitalA1() 
    {  
    	if("A1".equalsIgnoreCase(config.getTipoCertificado()))
    	{
    		return cert.getNotAfter();
    	}
    	else
    	{
    		return null;
    	}
    }  
}  