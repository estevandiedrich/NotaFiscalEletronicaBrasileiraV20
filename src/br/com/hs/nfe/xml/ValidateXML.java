package br.com.hs.nfe.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidateXML {
	private static final Logger logger = Logger.getLogger("ValidateXML");
	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	private String xsd = "";
	public ValidateXML(String xsd)
	{
		this.xsd = xsd;
	}
	public String validateXml3(String xml)
	{
	      try
	      {
	           DOMParser parser = new DOMParser();
	           parser.setFeature("http://xml.org/sax/features/validation", true);
	           parser.setFeature("http://apache.org/xml/features/validation/schema", true);
	           parser.setProperty(
	             "http://apache.org/xml/properties/schema/external-schemaLocation", 
	                        "http://www.portalfiscal.inf.br/nfe file:///C:/dev.estevan/workspace-java/NotaFiscalEletronicaBrasileiraV20/resources/PL_006n/enviNFe_v2.00.xsd");
	           MyErrorHandler errors = new MyErrorHandler();
	           parser.setErrorHandler(errors);
	           parser.parse("file:///C:/filas/83158824000111/enviNFe/xmlAssinados/NFe42120183158824000111550010000737431207726212.xml");
	     }
	     catch (Exception e) 
	     {
	         System.out.print("Problem parsing the file.");
	     }
	     return "";
	}
	public String validateXml2(String xml)
	{
		String erro = "";
		ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
		Source xmlFile = new StreamSource(bais);
		SchemaFactory schemaFactory = SchemaFactory
		    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File(xsd));
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Validator validator = schema.newValidator();
		try {
		  try {
			validator.validate(xmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
		  System.out.println(xmlFile.getSystemId() + " is NOT valid");
		  System.out.println("Reason: " + e.getLocalizedMessage());
		  erro = e.getLocalizedMessage();
		}
		return erro;
	}
	
	 public String validateXml(String xml) {  
		 String ret = "";
         try {  
        	 DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
     		documentFactory.setNamespaceAware(true);
     		documentFactory.setValidating(true);
     		documentFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
     		documentFactory.setAttribute(JAXP_SCHEMA_SOURCE, xsd);
     		DocumentBuilder parser = documentFactory.newDocumentBuilder();
     		ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
     		Document document = parser.parse(bais);
     		
     		// Create a SchemaFactory capable of understanding WXS schemas.
     		SchemaFactory factory = SchemaFactory
     				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
     		
     		// Load a WXS schema, represented by a Schema instance.
     		Source schemaFile = new StreamSource(new File(xsd));
     		Schema schema = factory.newSchema(schemaFile);
     		
     		// Create a Validator object, which can be used to validate
     		// an instance document.
     		Validator validator = schema.newValidator();
     		
     		// Validate the DOM tree.
     		validator.validate(new DOMSource(document)); 
   
     		IOUtils.closeQuietly(bais);
         } catch (ParserConfigurationException e) {  
             logger.error(e);
             ret = e.getMessage();
         } catch (SAXException e) {  
             logger.error(e);
             ret = e.getMessage();
         } catch (IOException e) {  
             logger.error(e);
             ret = e.getMessage();
         }  
         return ret;
     }  
	 
	 class MyErrorHandler implements ErrorHandler {
		  public void warning(SAXParseException e) throws SAXException {
		    show("Warning", e);
		    throw (e);
		  }

		  public void error(SAXParseException e) throws SAXException {
		    show("Error", e);
		    throw (e);
		  }

		  public void fatalError(SAXParseException e) throws SAXException {
		    show("Fatal Error", e);
		    throw (e);
		  }

		  private void show(String type, SAXParseException e) {
		    System.out.println(type + ": " + e.getMessage());
		    System.out.println("Line " + e.getLineNumber() + " Column " + e.getColumnNumber());
		    System.out.println("System ID: " + e.getSystemId());
		  }
	 }
}
