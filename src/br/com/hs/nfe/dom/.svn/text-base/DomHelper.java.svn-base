package br.com.hs.nfe.dom;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class DomHelper {
	private static final Logger logger = Logger.getLogger("DomHelper");
	public static synchronized Document createEmptyDocument()
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		Document doc = null;
        dbf.setNamespaceAware(true);
        try
        {
        	doc = dbf.newDocumentBuilder().newDocument();
        }
        catch(ParserConfigurationException pce)
        {
        	logger.error("Erro ao criar documento vazio", pce);
        }
		return doc;
	}
	public static synchronized String docToXML(Document doc, String encode)
    {
        ByteArrayOutputStream baos = null;
        String xmlEncoded = "";
        try
        {
            baos = new ByteArrayOutputStream();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(baos));
            baos.flush();
            baos.close();
            xmlEncoded = baos.toString("UTF-8");
        }
        catch(TransformerConfigurationException tce)
        {
            tce.printStackTrace();
        }
        catch(TransformerException te)
        {
            te.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return xmlEncoded;
    }
    public static synchronized Document createDocument(InputStream xmlStream)
    {
        Document document = null;
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
            document = parser.parse(xmlStream);
        }
        catch(ParserConfigurationException parserConfigurationException)
        {
            parserConfigurationException.printStackTrace();
        }
        catch(SAXException exception)
        {
            exception.printStackTrace();
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
        return document;
    }

	public static synchronized void docToXML(Document doc, File file) {
		try {
			DOMImplementationLS dim = (DOMImplementationLS) doc.getImplementation().getFeature("LS", "3.0");
			LSSerializer ser = dim.createLSSerializer();
			LSOutput lso = dim.createLSOutput();
			lso.setEncoding("UTF-8");
			
			FileOutputStream bout = new FileOutputStream(file);
			lso.setByteStream(bout);
			ser.write(doc, lso);
			IOUtils.closeQuietly(bout);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	public static synchronized final Document toDocument(byte[] msg) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder b = dbf.newDocumentBuilder();

		StreamSource s = new StreamSource();
		ByteArrayInputStream byteIN = new ByteArrayInputStream(msg);
		s.setInputStream(byteIN);

		return b.parse(s.getInputStream());
	}
	public static synchronized final Document toDocument(Node nd) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder b = dbf.newDocumentBuilder();

		Document doc = b.newDocument();
		ElementImpl newRoot = (ElementImpl) doc.importNode(nd, true);
		newRoot.normalize();
		doc.appendChild(newRoot);
		doc.normalize();
		return doc;
	}
	public static synchronized final byte[] toByte(Document doc) {
		DOMImplementationLS dim = (DOMImplementationLS) doc.getImplementation()
				.getFeature("LS", "3.0");
		LSSerializer ser = dim.createLSSerializer();
		LSOutput lso = dim.createLSOutput();
		lso.setEncoding("UTF-8");

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		lso.setByteStream(bout);
		ser.write(doc, lso);
		return bout.toByteArray();
	}
	public static synchronized ByteArrayOutputStream docToXML(Document doc)
	{
		try
		{
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
	        TransformerFactory tf = TransformerFactory.newInstance();  
	        Transformer trans = tf.newTransformer();  
	        trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        trans.transform(new DOMSource(doc), new StreamResult(os));
	        
	        os.flush();
	        os.close();
	        return os;
		}
		catch(TransformerConfigurationException tce)
		{
			logger.error("Problemas nas configuracoes do conversor de org.w3c.dom.Document para XML", tce);
		}
		catch(TransformerException te)
		{
			logger.error("Problemas ao converter org.w3c.dom.Document em XML", te);
		}
		catch(IOException ioe)
		{
			logger.error("Erro ao fechar o ByteArrayOutputStream", ioe);
		}
		return null;
	}
	public String getXmlContent(ByteArrayInputStream inputFilePath) {
		String xmlStream = "";
		InputStream is = inputFilePath;
		InputStreamReader isr = new InputStreamReader(is);
		try {
			BufferedReader reader = new BufferedReader(isr);
			String aux = null;
			while ((aux = reader.readLine()) != null) {
				xmlStream += aux;
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			logger.error("Problemas ao ler conteudo do xml", e);
		} 
		catch(UnsupportedEncodingException encodingException)
		{
			logger.error("Convertendo XML em String", encodingException);
		}
		catch (IOException e) 
		{
			logger.error("Problemas ao abrir arquivo para leitura",e);
		}
		return xmlStream;
	}
}
