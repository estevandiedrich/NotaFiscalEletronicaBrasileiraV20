package br.com.hs.nfe.signer;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.security.pkcs11.SunPKCS11;
import sun.security.pkcs11.wrapper.CK_C_INITIALIZE_ARGS;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.PrefixResolverDefault;

public class SignXML {

	private static PrivateKey privateKey;
	private static CK_C_INITIALIZE_ARGS a_sun_security_pkcs11_wrapper_CK_C_INITIALIZE_ARGS_fld;
	private static Certificate certificate = null;

	public static synchronized Document assinarXMLRemovendoEnviNFe(Document doc,Estabelecimento config)
			throws Exception {
		
		byte[] msg = DomHelper.toByte(doc);
		
		if ("enviNFe".equalsIgnoreCase(doc.getDocumentElement().getNodeName())) {
			Node nd = doc.getDocumentElement();
			NodeList nl = doc.getElementsByTagName("NFe");
			if (nl.getLength() != 1) {
				throw new Exception("Não foi possível encontrar a tag \"NFe\"");
			}
			nd = nl.item(0);
			doc = DomHelper.toDocument(nd);
		}

		Document nfe = assinarXML(doc,config);

		Document enviDoc = DomHelper.toDocument(msg);
		Node nd = enviDoc.getDocumentElement();
		NodeList nl = enviDoc.getElementsByTagName("NFe");
		if (nl.getLength() != 1) {
			throw new Exception("Não foi possível encontrar a tag \"NFe\"");
		}
		nd = nl.item(0);
		nd.getParentNode().removeChild(nd);
		
		
		ElementImpl newRoot = (ElementImpl) enviDoc.importNode(nfe.getDocumentElement(), true);
		newRoot.normalize();
		enviDoc.getDocumentElement().appendChild(newRoot);
		enviDoc.normalizeDocument();
		
		return enviDoc;
	}
	@SuppressWarnings("unchecked")
	public static Map recuperarCertificadosRepositorio2(String repositorioInfo, String senha)
    	throws Exception
	{
	    Map certificadosValidos = new HashMap();
	    File certFile = new File(repositorioInfo);
	    if(!certFile.exists())
	        throw new Exception(MessageFormat.format("A biblioteca {0} associada a este Smart Card/Token n\343o foi encontrada.Por favor v\341 no menu Sistema, Certificados e clique em Procurar Dispositivos.", new Object[] {
	            repositorioInfo
	        }));
	    if(!certFile.canRead())
	        throw new Exception(MessageFormat.format("A biblioteca {0} associada a este Smart Card/Token  n\343o possui permiss\343o de leitura.", new Object[] {
	            repositorioInfo
	        }));
	    PKCS11 pkcs11;
	    long portasDisponiveis[];
	    try
	    {
	        pkcs11 = PKCS11.getInstance(repositorioInfo, "C_GetFunctionList", a_sun_security_pkcs11_wrapper_CK_C_INITIALIZE_ARGS_fld, false);
	        portasDisponiveis = pkcs11.C_GetSlotList(false);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new Exception("Ocorreu um erro ao tentar recuperar o certificado A3 - Verifique se o dispositivo est\341 conectado corretamente.");
	    }
	    for(int i = 0; i < portasDisponiveis.length;)
	        try
	        {
	            String label = (new String(pkcs11.C_GetTokenInfo(portasDisponiveis[i]).label)).trim();
	            if(!repositorioInfo.equals(label))
	                continue;
	            //repositorioInfo.setSlot(Long.valueOf(portasDisponiveis[i]));
	            FileInputStream fileInputStream = new FileInputStream(certFile);
	            Provider pkcs11Provider = new SunPKCS11(fileInputStream);
	            Security.addProvider(pkcs11Provider);
	            KeyStore keyStore = KeyStore.getInstance("PKCS11");
	            keyStore.load(null, senha.toCharArray());
	            String alias = "";
	            X509Certificate certificate = null;
	            PrivateKey privateKey = null;
	            for(Enumeration e = keyStore.aliases(); e.hasMoreElements(); certificadosValidos.put(certificate, privateKey))
	            {
	                alias = (String)e.nextElement();
	                certificate = (X509Certificate)keyStore.getCertificate(alias);
	                privateKey = (PrivateKey)keyStore.getKey(alias, null);
	            }
	
	            break;
	        }
	        catch(PKCS11Exception e)
	        {
	        	throw new Exception("Ocorreu um problema na tentativa de acesso ao certificado");
	        }
	
	    if(certificadosValidos.size() > 0)
	        return certificadosValidos;
	    else
	        return null;
	}
	public static synchronized Document assinarXMLA3(Document doc,Estabelecimento config) 
		throws Exception
	{
		Map certificadosValidos = recuperarCertificadosRepositorio2("","");
		Iterator it = certificadosValidos.keySet().iterator();
        do
        {
            if(!it.hasNext())
                break;
            X509Certificate certificate = (X509Certificate)it.next();
        } while(true);
		assinarXML(doc, privateKey, certificate);
		
		return doc;
	}
	public static synchronized Document assinarXML(Document doc,Estabelecimento config)
			throws Exception {
		KeyStore ks = KeyStore.getInstance("JKS");
		InputStream is = new FileInputStream(config.getJks());
		ks.load(is, config.getJksPassword().toCharArray());
		is.close();
		// TODO Buscar alias.
		Key key = ks.getKey(config.getPfxAlias(), config.getPfxPassword().toCharArray());
		if (key instanceof PrivateKey) {
			privateKey = (PrivateKey) key;
			certificate = ks.getCertificate(config.getPfxAlias());
		}
		return assinarXML(doc, privateKey, certificate);
	}

	public static synchronized Document assinarXML(Document doc, PrivateKey privateKey,
			Certificate certificate) throws Exception {
		XMLSignatureFactory xmlSignFact = null;
		try {
			String providerName = System.getProperty("jsr105Provider",
					"org.jcp.xml.dsig.internal.dom.XMLDSigRI");
			xmlSignFact = XMLSignatureFactory.getInstance("DOM",
					(Provider) Class.forName(providerName).newInstance());
		} catch (Exception ie) {
			throw ie;
		}

		DOMSignContext signContext = new DOMSignContext(privateKey, doc
				.getDocumentElement());
		XMLSignature signature = xmlSignFact.newXMLSignature(criarSignedInfo(
				doc, xmlSignFact), criarKeyInfo(certificate));

		signature.sign(signContext);

		return doc;
	}

	public static synchronized KeyInfo criarKeyInfo(Certificate certificate) throws Exception {
		KeyInfoFactory kif = KeyInfoFactory.getInstance("DOM");
		X509Data x509Data = kif.newX509Data(Collections
				.singletonList(certificate));
		return kif.newKeyInfo(Collections.singletonList(x509Data));
	}// newKeyInfo(...)

	public static synchronized SignedInfo criarSignedInfo(Document doc,
			XMLSignatureFactory xmlSignFact) throws Exception {
		SignatureMethod sm = xmlSignFact.newSignatureMethod(
				SignatureMethod.RSA_SHA1, null);
		DigestMethod digestMethod = xmlSignFact.newDigestMethod(
				DigestMethod.SHA1, null);
		CanonicalizationMethod cm = xmlSignFact.newCanonicalizationMethod(
				CanonicalizationMethod.INCLUSIVE,
				(C14NMethodParameterSpec) null);

		TransformParameterSpec transformSpec = null;
		Transform envTransformEnveloped = xmlSignFact.newTransform(
				Transform.ENVELOPED, transformSpec);
		Transform envTransformC14N = xmlSignFact.newTransform(
				CanonicalizationMethod.INCLUSIVE, transformSpec);

		ArrayList<Transform> transformList = new ArrayList<Transform>();
		transformList.add(envTransformEnveloped);
		transformList.add(envTransformC14N);

		String referenceURIValue = null;
		if (getReferencia() != null && getReferencia().length() > 0) {
			System.out.println("-->" + doc.getDocumentElement().getNodeName());
			final PrefixResolver resolver = new PrefixResolverDefault(doc
					.getDocumentElement());

			NamespaceContext ctx = new NamespaceContext() {
				public String getNamespaceURI(String prefix) {
					return resolver.getNamespaceForPrefix(prefix);
				}

				public Iterator<?> getPrefixes(String val) {
					return null;
				}

				public String getPrefix(String uri) {
					return null;
				}
			};

			XPathFactory xpathFact = XPathFactory.newInstance();
			XPath xpath = xpathFact.newXPath();
			xpath.setNamespaceContext(ctx);
			doc.normalizeDocument();
			referenceURIValue = xpath.evaluate(getReferencia(), doc);
		}

		if (referenceURIValue != null) {
			referenceURIValue = "#" + referenceURIValue;
		} else if (getReferencia().startsWith("http://")) {
			referenceURIValue = getReferencia();
		} else {
			referenceURIValue = "";
		}

		Reference ref = xmlSignFact.newReference(referenceURIValue,
				digestMethod, transformList, null, null);
		ArrayList<Reference> refList = new ArrayList<Reference>();
		refList.add(ref);

		return xmlSignFact.newSignedInfo(cm, sm, refList);
	}

	private static synchronized String getReferencia() {
		return "//:NFe/:infNFe/@Id";
	}

//	public static void main(String[] args) throws Exception {
//		File f = new File("C:/filas/83158824000626/cancNFe/xml","42110605537225000286550010000031331200071763ped-canc.xml");
//		byte[] msg = IOUtils.toByteArray(new FileInputStream(f));
//		Document doc = DomHelper.toDocument(msg);
//		doc = SignXML.assinarXMLRemovendoEnviNFe(doc);
//		System.out.println(new String(DomHelper.toByte(doc)));
//	}
}
