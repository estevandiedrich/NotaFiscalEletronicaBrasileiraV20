package br.com.hs.nfe.util;

import java.text.Normalizer;

public class TrataCaracter { 
	public static void main(String... str)
	{
		String str2 = "§º€åÂÀÁÄÃâãàáäÊÈÉËêèéëÎÍÌÏîíìïÔÕÒÓÖôõòóöÛÙÚÜûúùüÇçıÿÑñ";
		System.out.println(trata(str2));
	}
   public static synchronized String trata (String passa){
	  passa = Normalizer.normalize(passa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
      /*passa = passa.replaceAll("[ÂÀÁÄÃ]","A");   
      passa = passa.replaceAll("[âãàáä]","a");   
      passa = passa.replaceAll("[ÊÈÉË]","E");   
      passa = passa.replaceAll("[êèéë]","e");   
      passa = passa.replaceAll("ÎÍÌÏ","I");   
      passa = passa.replaceAll("îíìï","i");   
      passa = passa.replaceAll("[ÔÕÒÓÖ]","O");   
      passa = passa.replaceAll("[ôõòóö]","o");   
      passa = passa.replaceAll("[ÛÙÚÜ]","U");   
      passa = passa.replaceAll("[ûúùü]","u");   
      passa = passa.replaceAll("Ç","C");   
      passa = passa.replaceAll("ç","c");    
      passa = passa.replaceAll("[ıÿ]","y");   
      passa = passa.replaceAll("İ","Y");   
      passa = passa.replaceAll("ñ","n");   
      passa = passa.replaceAll("Ñ","N");
      passa = passa.replaceAll("\\t", "");
      passa = passa.replaceAll("\\n", "");
      passa = passa.replaceAll("§", "");
      passa = passa.replaceAll("º", "");
      passa = passa.replaceAll("€", "");
      passa = passa.replaceAll("å", "");*/
      return passa;   
   }   
}  

