package br.com.hs.nfe.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.compiler.Txt2XmlInutNFeParser;

public class InutNFeParser {
	private static final Logger logger = Logger.getLogger("InutNFeParser");
	public static synchronized void txt2XmlParser(FileReader txt,Document xml)
	{
		logger.info("Convertendo arquivo");
		String thisLine = "";
	    try 
	    {
	    	BufferedReader br = new BufferedReader(txt);
	    	while ((thisLine = br.readLine()) != null) 
	    	{
	    		Txt2XmlInutNFeParser.txt2XmlInutNFeParser(thisLine, xml);
	    	} 
	    }
	    catch (IOException e) 
	    {
	       logger.error("Error: " + e);
	    }
	}
}
