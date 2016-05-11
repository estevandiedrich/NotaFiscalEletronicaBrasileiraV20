package br.com.hs.nfe.signer;

import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

import sun.security.pkcs11.SunPKCS11;
import sun.security.pkcs11.wrapper.CK_C_INITIALIZE_ARGS;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import br.com.hs.nfe.base.ThreadBase;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.file.FileManager;
import br.com.hs.nfe.vo.CertificadosVO;
import br.gov.sp.fazenda.dsge.certif.RepositorioInfo;
import br.gov.sp.fazenda.dsge.common.util.StringHelper;

public class CarregaCertificadoA3 extends ThreadBase{
	private static final Logger logger = Logger.getLogger("CarregaCertificadoA3");
	public static String A3 = "A3";
	private CK_C_INITIALIZE_ARGS a_sun_security_pkcs11_wrapper_CK_C_INITIALIZE_ARGS_fld;
	private Estabelecimento config = null;
	public CarregaCertificadoA3(Estabelecimento config)
	{
		this.config = config;
	}
	public void run()
	{
		if("A3".equalsIgnoreCase(config.getTipoCertificado()))
        { 
//			System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//		    System.setProperty("javax.net.ssl.trustStore", config.getJks());
//		    System.setProperty("javax.net.ssl.trustStorePassword", config.getJksPassword());
			
			String sistemaOperacionalStr = System.getProperty("os.name") == null ? "" : System.getProperty("os.name").toLowerCase();
			String defaultSearchPath = "";
			String libraryExtension = "";
			String caminho = "";
	        if(sistemaOperacionalStr.indexOf("windows") >= 0)
	        {
	            String windir = System.getenv("WINDIR");
	            if(StringHelper.isBlankOrNull(windir))
	            {
	                logger.debug("incluirA3BuscaAutomatica - n\343o conseguiu achar a vari\341vel de ambiente WINDIR");
	                windir = (new StringBuilder()).append("C:").append(File.separator).append("WINDOWS").toString();
	            }
	            libraryExtension = ".dll";
	            defaultSearchPath = (new StringBuilder()).append(windir).append(File.separator).append("system32").append(File.separator).toString();
	        } else
	        if(sistemaOperacionalStr.indexOf("linux") >= 0)
	        {
	            libraryExtension = ".so";
	            defaultSearchPath = "/usr/lib/";
	        } else
	        {
	            logger.debug("incluirA3BuscaAutomatica - n\343o \351 compat\355vel com outros sistemas operacionais");
	        }
	        if(StringHelper.isBlankOrNull(caminho))
	            caminho = defaultSearchPath;
	        File searchFile = new File(caminho);
	        List<RepositorioInfo> listaRepositorio = new ArrayList<RepositorioInfo>();
	        File files[] = {
	            searchFile
	        };
	        if(searchFile.isDirectory())
	            files = FileManager.getInstance().getFiles(caminho,libraryExtension.trim().toLowerCase());
	        for(int i=0;i < files.length;i++)
	        {
	        	File file = files[i];
	        	try
	        	{
		        	PKCS11 pkcs11 = PKCS11.getInstance(file.getAbsolutePath(), "C_GetFunctionList", a_sun_security_pkcs11_wrapper_CK_C_INITIALIZE_ARGS_fld, false);
		        	long portasDisponiveis[] = pkcs11.C_GetSlotList(false);
		        	RepositorioInfo repositorioInfo = null;
	    			for(int j=0;j < portasDisponiveis.length;j++)
	    			{
	    				try
	    				{
		                    String label = (new String(pkcs11.C_GetTokenInfo(portasDisponiveis[j]).label)).trim();
		                    if(label != null && label.trim().length() > 0)
		                    {
		                    	repositorioInfo = new RepositorioInfo();
		                        repositorioInfo.setNome(label);
		                        repositorioInfo.setSlot(Long.valueOf(portasDisponiveis[j]));
		                        repositorioInfo.setCaminho(file.getAbsolutePath());
		                        repositorioInfo.setTpCertif(A3);
		                        if(!listaRepositorio.contains(repositorioInfo))
		                        {
		                            Provider pkcs11Provider = new SunPKCS11(repositorioInfo.getFileConfiguration());
		                            Security.addProvider(pkcs11Provider);
		                            KeyStore keyStore = KeyStore.getInstance("PKCS11");
		                            keyStore.load(null, config.getPfxPassword().toCharArray());
		                            
		                            String alias = "";
		                            X509Certificate certificate = null;
		                            PrivateKey privateKey = null;
		                            for(Enumeration<String> e = keyStore.aliases(); e.hasMoreElements(); CertificadoA3Singleton.getInstance().getCertificados().add(new CertificadosVO(certificate, privateKey, config.getCnpj())))
		                            {
		                                alias = (String)e.nextElement();
		                                certificate = (X509Certificate)keyStore.getCertificate(alias);
		                                privateKey = (PrivateKey)keyStore.getKey(alias, null);
		                            }
		                            listaRepositorio.add(repositorioInfo);
		                        }
		                    }
	    				}
	                    catch(PKCS11Exception e) { 
	                    }
	    			}
	        	}
	        	catch (Exception e) {
				}
	        }
	        caminho = null;
	        libraryExtension = null;
	        defaultSearchPath = null;
	        sistemaOperacionalStr = null;
	        files = null;
        }
	}
}
