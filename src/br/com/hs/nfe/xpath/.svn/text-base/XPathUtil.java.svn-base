package br.com.hs.nfe.xpath;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.PrefixResolverDefault;
import org.w3c.dom.Document;

import br.com.hs.nfe.dom.DomHelper;

public class XPathUtil {
	public static synchronized String solveXPath(InputStream is, String xPath) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		IOUtils.copy(is, baos);
		byte[] msg = baos.toByteArray();
		Document doc = DomHelper.toDocument(msg);
		final PrefixResolver resolver = new PrefixResolverDefault(doc.getDocumentElement());
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
		return xpath.evaluate(xPath, doc);
	}
}
