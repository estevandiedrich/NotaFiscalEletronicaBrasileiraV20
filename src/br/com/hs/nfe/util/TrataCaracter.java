package br.com.hs.nfe.util;

import java.text.Normalizer;

public class TrataCaracter { 
	public static void main(String... str)
	{
		String str2 = "������������������������������������������������������";
		System.out.println(trata(str2));
	}
   public static synchronized String trata (String passa){
	  passa = Normalizer.normalize(passa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
      /*passa = passa.replaceAll("[�����]","A");   
      passa = passa.replaceAll("[�����]","a");   
      passa = passa.replaceAll("[����]","E");   
      passa = passa.replaceAll("[����]","e");   
      passa = passa.replaceAll("����","I");   
      passa = passa.replaceAll("����","i");   
      passa = passa.replaceAll("[�����]","O");   
      passa = passa.replaceAll("[�����]","o");   
      passa = passa.replaceAll("[����]","U");   
      passa = passa.replaceAll("[����]","u");   
      passa = passa.replaceAll("�","C");   
      passa = passa.replaceAll("�","c");    
      passa = passa.replaceAll("[��]","y");   
      passa = passa.replaceAll("�","Y");   
      passa = passa.replaceAll("�","n");   
      passa = passa.replaceAll("�","N");
      passa = passa.replaceAll("\\t", "");
      passa = passa.replaceAll("\\n", "");
      passa = passa.replaceAll("�", "");
      passa = passa.replaceAll("�", "");
      passa = passa.replaceAll("�", "");
      passa = passa.replaceAll("�", "");*/
      return passa;   
   }   
}  

