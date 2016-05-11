package br.com.hs.nfe.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import br.com.hs.nfe.compiler.Txt2XmlEnviNFeParser;

public class EnviNFeParser {
	private static final Logger logger = Logger.getLogger("EnviNFeParser");
	public static synchronized void txt2XmlParser(FileReader txt,Document xml,HashMap<String, String> params)
	{
		logger.info("Convertendo arquivo");
		String thisLine = "";
	    try 
	    {
	    	BufferedReader br = new BufferedReader(txt);
	    	while ((thisLine = br.readLine()) != null) 
	    	{
	    		Txt2XmlEnviNFeParser.txt2XmlEnviNFeParser(thisLine, xml, params);
	    	} 
	    }
	    catch (IOException e) 
	    {
	       logger.error("Error: " + e);
	    }
	}
}
