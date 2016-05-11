// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientConfig.java

package br.com.hs.nfe.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.configuration.jsse.TLSClientParameters;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.signer.CertificadoA3Singleton;
import br.com.hs.nfe.vo.CertificadosVO;

public class ClientConfig
{
	private Estabelecimento config = null;
	public static String A3 = "A3";
    public ClientConfig(Estabelecimento config)
    {
    	this.config = config;
    }

    public void configTLS(TLSClientParameters tls)
        throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(new FileInputStream(config.getJks()), baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        String type = "JKS";
        char password[] = config.getJksPassword().toCharArray();
        KeyStore ks = KeyStore.getInstance(type);
        ks.load(bais, password);
		
        KeyManager kmgrs[] = null;
        if("A3".equalsIgnoreCase(config.getTipoCertificado()))
        {        	
        	PrivateKey privateKey = null;
    		X509Certificate certificate = null;
    	    CertificadosVO certificadosVO = CertificadoA3Singleton.getInstance().getPorCnpj(config.getCnpj());
    	    privateKey = certificadosVO.getPrivateKey();
    	    certificate = certificadosVO.getCertificate();
        	kmgrs = new KeyManager[]{new HSKeyManager(certificate,privateKey)};
        }
        else
        {
        	kmgrs = getKeyManagers(ks, config.getPfxPassword());
        }
        TrustManager tmgrs[] = getTrustManagers(ks);
        tls.setKeyManagers(kmgrs);
        tls.setTrustManagers(tmgrs);
    }

    public KeyManager[] getKeyManagers(KeyStore keyStore, String keyPassword)
        throws GeneralSecurityException, IOException
    {
        String alg = KeyManagerFactory.getDefaultAlgorithm();
        char keyPass[] = keyPassword == null ? null : keyPassword.toCharArray();
        KeyManagerFactory fac = KeyManagerFactory.getInstance(alg);
        fac.init(keyStore, keyPass);
        return fac.getKeyManagers();
    }
    public KeyManager[] getKeyManagersA3(KeyStore iks,String password)
    	throws GeneralSecurityException, IOException
    {
    	String alg = KeyManagerFactory.getDefaultAlgorithm();
    	KeyManagerFactory kmf = KeyManagerFactory.getInstance(alg);
    	kmf.init(iks, password.toCharArray());
    	return kmf.getKeyManagers();
    }
    public TrustManager[] getTrustManagers(KeyStore keyStore)
        throws GeneralSecurityException, IOException
    {
        String alg = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory fac = TrustManagerFactory.getInstance(alg);
        fac.init(keyStore);
        return fac.getTrustManagers();
    }
}
