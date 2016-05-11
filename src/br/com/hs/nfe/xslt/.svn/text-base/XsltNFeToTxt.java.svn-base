package br.com.hs.nfe.xslt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XsltNFeToTxt {
	private static final Logger logger = Logger.getLogger("XsltNFeToTxt");
	private String arquivo;
	public XsltNFeToTxt(String arquivo)
	{
		this.arquivo = arquivo;
	}
	public byte[] transformar(byte[] msg) 
	{
		InputStream xslt = Thread.currentThread().getContextClassLoader().getResourceAsStream(arquivo);
		SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		Properties props = new Properties();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(msg);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// saxtransformerfactory.setURIResolver(this);
		TransformerHandler transformerhandler = null;
		try {
			transformerhandler = saxTransformerFactory.newTransformerHandler(new StreamSource(xslt));
			transformerhandler.setResult(new StreamResult(baos));
			transformerhandler.getTransformer().setParameter("Properties", props);
			transformerhandler.getTransformer().setParameter("Variables", new Properties());
			XMLReader xmlreader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
			xmlreader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			xmlreader.setFeature("http://xml.org/sax/features/validation", false);
			xmlreader.setFeature("http://apache.org/xml/features/validation/schema", false);
			// xmlreader.setEntityResolver(er);
			// xmlreader.setErrorHandler(eh);
			xmlreader.setContentHandler(transformerhandler);
			// errors = new StringBuffer();
			xmlreader.parse(new InputSource(bais));
		
		} catch (SAXException e1)
		{
			logger.error(e1);
		} catch (IOException e2)
		{
			logger.error(e2);
		} catch(TransformerConfigurationException e)
		{
			
		}
		return baos.toByteArray();
	}
}
