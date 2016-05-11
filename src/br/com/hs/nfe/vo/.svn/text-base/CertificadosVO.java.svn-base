package br.com.hs.nfe.vo;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CertificadosVO {
	public CertificadosVO(X509Certificate certificate,PrivateKey privateKey,String cnpj)
	{
		this.privateKey = privateKey;
		this.certificate = certificate;
		this.cnpj = cnpj;
	}
	private PrivateKey privateKey = null;
	private X509Certificate certificate = null;
	private String cnpj = "";
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public X509Certificate getCertificate() {
		return certificate;
	}
	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
