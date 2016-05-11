package br.com.hs.nfe.signer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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

import org.apache.commons.io.IOUtils;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.hs.nfe.dom.DomHelper;
import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.vo.CertificadosVO;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.PrefixResolverDefault;

public class AssinarXML {
	private PrivateKey privateKey = null;
	private String referencia = "";
	private Estabelecimento config = null;
	private Certificate certificate = null;
	
	public static void main(String... s)
	{
		AssinarXML assinarXML = new AssinarXML();
		try {
			File sefazErpEnviNFe = new File("C:/filas/14589140000151/enviNFe/xmlValidos/NFe42120614589140000151550010000000251871930120 (2).xml");
			FileInputStream fis = new FileInputStream(sefazErpEnviNFe);
			Document doc = DomHelper.toDocument(IOUtils.toByteArray(fis));
			IOUtils.closeQuietly(fis);
			doc = assinarXML.assinarXMLA3RemovendoEnviNFe(doc);
			DomHelper.docToXML(doc,new File("C:/filas/14589140000151/enviNFe/xmlAssinados/NFe42120614589140000151550010000000251871930120 (2).xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public AssinarXML()
	{
		
	}
	public AssinarXML(Estabelecimento config,String referencia)
	{
		this.config = config;
		this.referencia = referencia;
	}
	
	public Document assinarXMLRemovendoEnvEvento(Document doc)
	throws Exception
	{
		byte[] msg = DomHelper.toByte(doc);
		
		if ("envEvento".equalsIgnoreCase(doc.getDocumentElement().getNodeName())) {
			Node nd = doc.getDocumentElement();
			NodeList nl = doc.getElementsByTagName("evento");
			if (nl.getLength() != 1) {
				throw new Exception("N�o foi poss�vel encontrar a tag \"evento\"");
			}
			nd = nl.item(0);
			doc = DomHelper.toDocument(nd);
		}
		
		Document nfe = assinarXML(doc);
		
		Document enviDoc = DomHelper.toDocument(msg);
		Node nd = enviDoc.getDocumentElement();
		NodeList nl = enviDoc.getElementsByTagName("evento");
		if (nl.getLength() != 1) {
			throw new Exception("N�o foi poss�vel encontrar a tag \"evento\"");
		}
		nd = nl.item(0);
		nd.getParentNode().removeChild(nd);
		
		
		ElementImpl newRoot = (ElementImpl) enviDoc.importNode(nfe.getDocumentElement(), true);
		newRoot.normalize();
		enviDoc.getDocumentElement().appendChild(newRoot);
		enviDoc.normalizeDocument();
		
		return enviDoc;
	}
	public Document assinarXMLA3RemovendoEnviNFe(Document doc)
	throws Exception {

		byte[] msg = DomHelper.toByte(doc);
		
		if ("enviNFe".equalsIgnoreCase(doc.getDocumentElement().getNodeName())) {
			Node nd = doc.getDocumentElement();
			NodeList nl = doc.getElementsByTagName("NFe");
			if (nl.getLength() != 1) {
				throw new Exception("N�o foi poss�vel encontrar a tag \"NFe\"");
			}
			nd = nl.item(0);
			doc = DomHelper.toDocument(nd);
		}
		
		Document nfe = assinarXMLA3(doc);
		
		Document enviDoc = DomHelper.toDocument(msg);
		Node nd = enviDoc.getDocumentElement();
		NodeList nl = enviDoc.getElementsByTagName("NFe");
		if (nl.getLength() != 1) {
			throw new Exception("N�o foi poss�vel encontrar a tag \"NFe\"");
		}
		nd = nl.item(0);
		nd.getParentNode().removeChild(nd);
		
		
		ElementImpl newRoot = (ElementImpl) enviDoc.importNode(nfe.getDocumentElement(), true);
		newRoot.normalize();
		enviDoc.getDocumentElement().appendChild(newRoot);
		enviDoc.normalizeDocument();
		
		return enviDoc;
	}
	public Document assinarXMLRemovendoEnviNFe(Document doc)
	throws Exception {

		byte[] msg = DomHelper.toByte(doc);
		
		if ("enviNFe".equalsIgnoreCase(doc.getDocumentElement().getNodeName())) {
			Node nd = doc.getDocumentElement();
			NodeList nl = doc.getElementsByTagName("NFe");
			if (nl.getLength() != 1) {
				throw new Exception("N�o foi poss�vel encontrar a tag \"NFe\"");
			}
			nd = nl.item(0);
			doc = DomHelper.toDocument(nd);
		}
		
		Document nfe = assinarXML(doc);
		
		Document enviDoc = DomHelper.toDocument(msg);
		Node nd = enviDoc.getDocumentElement();
		NodeList nl = enviDoc.getElementsByTagName("NFe");
		if (nl.getLength() != 1) {
			throw new Exception("N�o foi poss�vel encontrar a tag \"NFe\"");
		}
		nd = nl.item(0);
		nd.getParentNode().removeChild(nd);
		
		
		ElementImpl newRoot = (ElementImpl) enviDoc.importNode(nfe.getDocumentElement(), true);
		newRoot.normalize();
		enviDoc.getDocumentElement().appendChild(newRoot);
		enviDoc.normalizeDocument();
		
		return enviDoc;
	}
	
	public synchronized Document assinarXMLA3(Document doc) 
		throws Exception
	{
		CertificadosVO certificadosVO = CertificadoA3Singleton.getInstance().getPorCnpj(config.getCnpj());
		certificate = certificadosVO.getCertificate();
		privateKey = certificadosVO.getPrivateKey();
	    return assinarXML(doc, privateKey, certificate);
	}
	public Document assinarXML(Document doc)
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

	public Document assinarXML(Document doc, PrivateKey privateKey,
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

	public KeyInfo criarKeyInfo(Certificate certificate) throws Exception {
		KeyInfoFactory kif = KeyInfoFactory.getInstance("DOM");
		X509Data x509Data = kif.newX509Data(Collections
				.singletonList(certificate));
		return kif.newKeyInfo(Collections.singletonList(x509Data));
	}// newKeyInfo(...)

	public SignedInfo criarSignedInfo(Document doc,
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

	private String getReferencia() {
		return this.referencia;
	}
}
