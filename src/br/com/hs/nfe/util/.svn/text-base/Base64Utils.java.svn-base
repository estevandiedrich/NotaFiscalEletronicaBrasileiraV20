package br.com.hs.nfe.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	
	public static String encode(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	
	public static String decode(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(encode("1234"));
		
	}
}
