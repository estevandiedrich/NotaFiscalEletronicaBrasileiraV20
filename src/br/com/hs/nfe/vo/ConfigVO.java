package br.com.hs.nfe.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("configVO")
public class ConfigVO {
	private String enviNFeTXT = "";
	private String consReciNFeTXT = "";
	private String enviNFeXML = "";
	private String enviNFeXSD = "";
	private String enviNFeXMLAssinados = "";
	private String enviNFeXMLValidos = "";
	private String enviNFeXMLInvalidos = "";
	private String enviNFeXMLEnviados = "";
	private String enviNFeXMLAutorizados = "";
	private String enviNFeXMLDenegados = "";
	private String enviNFeXMLProcessados = "";
	private String enviarEmail = "";
	private String servidorEmail = "";	
	private String portaEmail = "";
	private String assuntoEmail = "";
	private String emailDe = "";
	private String emailPara = "";
	private String usuarioEmail = "";
	private String senhaEmail = "";
	private String jks = "";
	private String jksPassword = "";
	private String pfxPassword = "";
	private String pfxAlias = "";
	private String gerarDanfe = "";
	private String danfe = "";
	private String imprimirDanfe = "";
	private String impressora = "";
	private String logoDanfe = "";
	private String cnpj = "";
	private String pe = "";
	private String cUF = "";
	private String tpAmb = "";
	private String compactarXML = "";
	private String xmlCompactado = "";
	public String getCompactarXML() {
		return compactarXML;
	}
	public void setCompactarXML(String compactarXML) {
		this.compactarXML = compactarXML;
	}
	public String getGerarDanfe() {
		return gerarDanfe;
	}
	public void setGerarDanfe(String gerarDanfe) {
		this.gerarDanfe = gerarDanfe;
	}
	public String getCUF() {
		return cUF;
	}
	public void setCUF(String cuf) {
		cUF = cuf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	public String getLogoDanfe() {
		return logoDanfe;
	}
	public void setLogoDanfe(String logoDanfe) {
		this.logoDanfe = logoDanfe;
	}
	public String getImpressora() {
		return impressora;
	}
	public String getConsReciNFeTXT() {
		return consReciNFeTXT;
	}
	public void setConsReciNFeTXT(String consReciNFeTXT) {
		this.consReciNFeTXT = consReciNFeTXT;
	}
	public void setImpressora(String impressora) {
		this.impressora = impressora;
	}
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	public String getSenhaEmail() {
		return senhaEmail;
	}
	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = senhaEmail;
	}
	public String getEmailDe() {
		return emailDe;
	}
	public void setEmailDe(String emailDe) {
		this.emailDe = emailDe;
	}
	public String getEmailPara() {
		return emailPara;
	}
	public void setEmailPara(String emailPara) {
		this.emailPara = emailPara;
	}
	public String getServidorEmail() {
		return servidorEmail;
	}
	public void setServidorEmail(String servidorEmail) {
		this.servidorEmail = servidorEmail;
	}
	public String getPortaEmail() {
		return portaEmail;
	}
	public void setPortaEmail(String portaEmail) {
		this.portaEmail = portaEmail;
	}
	public String getEnviNFeXMLProcessados() {
		return enviNFeXMLProcessados;
	}
	public void setEnviNFeXMLProcessados(String enviNFeXMLProcessados) {
		this.enviNFeXMLProcessados = enviNFeXMLProcessados;
	}
	public String getDanfe() {
		return danfe;
	}
	public void setDanfe(String danfe) {
		this.danfe = danfe;
	}
	public String getEnviNFeXMLDenegados() {
		return enviNFeXMLDenegados;
	}
	public void setEnviNFeXMLDenegados(String enviNFeXMLDenegados) {
		this.enviNFeXMLDenegados = enviNFeXMLDenegados;
	}
	public String getEnviNFeXMLAutorizados() {
		return enviNFeXMLAutorizados;
	}
	public void setEnviNFeXMLAutorizados(String enviNFeXMLAutorizados) {
		this.enviNFeXMLAutorizados = enviNFeXMLAutorizados;
	}
	
	public synchronized String getEnviNFeTXT() {
		return enviNFeTXT;
	}
	public synchronized void setEnviNFeTXT(String enviNFeTXT) {
		this.enviNFeTXT = enviNFeTXT;
	}
	public synchronized String getEnviNFeXML() {
		return enviNFeXML;
	}
	public synchronized void setEnviNFeXML(String enviNFeXML) {
		this.enviNFeXML = enviNFeXML;
	}
	public synchronized String getEnviNFeXSD() {
		return enviNFeXSD;
	}
	public synchronized void setEnviNFeXSD(String enviNFeXSD) {
		this.enviNFeXSD = enviNFeXSD;
	}
	public synchronized String getEnviNFeXMLAssinados() {
		return enviNFeXMLAssinados;
	}
	public synchronized void setEnviNFeXMLAssinados(String enviNFeXMLAssinados) {
		this.enviNFeXMLAssinados = enviNFeXMLAssinados;
	}
	public synchronized String getEnviNFeXMLValidos() {
		return enviNFeXMLValidos;
	}
	public synchronized void setEnviNFeXMLValidos(String enviNFeXMLValidos) {
		this.enviNFeXMLValidos = enviNFeXMLValidos;
	}
	public synchronized String getEnviNFeXMLInvalidos() {
		return enviNFeXMLInvalidos;
	}
	public synchronized void setEnviNFeXMLInvalidos(String enviNFeXMLInvalidos) {
		this.enviNFeXMLInvalidos = enviNFeXMLInvalidos;
	}
	public synchronized String getEnviNFeXMLEnviados() {
		return enviNFeXMLEnviados;
	}
	public synchronized void setEnviNFeXMLEnviados(String enviNFeXMLEnviados) {
		this.enviNFeXMLEnviados = enviNFeXMLEnviados;
	}
	public synchronized String getJks() {
		return jks;
	}
	public synchronized void setJks(String jks) {
		this.jks = jks;
	}
	public synchronized String getJksPassword() {
		return jksPassword;
	}
	public synchronized void setJksPassword(String jksPassword) {
		this.jksPassword = jksPassword;
	}
	public synchronized String getPfxPassword() {
		return pfxPassword;
	}
	public synchronized void setPfxPassword(String pfxPassword) {
		this.pfxPassword = pfxPassword;
	}
	public synchronized String getPfxAlias() {
		return pfxAlias;
	}
	public synchronized void setPfxAlias(String pfxAlias) {
		this.pfxAlias = pfxAlias;
	}
	public String getXmlCompactado() {
		return xmlCompactado;
	}
	public void setXmlCompactado(String xmlCompactado) {
		this.xmlCompactado = xmlCompactado;
	}
	public String getImprimirDanfe() {
		return imprimirDanfe;
	}
	public void setImprimirDanfe(String imprimirDanfe) {
		this.imprimirDanfe = imprimirDanfe;
	}
	public String getTpAmb() {
		return tpAmb;
	}
	public void setTpAmb(String tpAmb) {
		this.tpAmb = tpAmb;
	}
	public String getEnviarEmail() {
		return enviarEmail;
	}
	public void setEnviarEmail(String enviarEmail) {
		this.enviarEmail = enviarEmail;
	}
	public String getAssuntoEmail() {
		return assuntoEmail;
	}
	public void setAssuntoEmail(String assuntoEmail) {
		this.assuntoEmail = assuntoEmail;
	}
	
}
