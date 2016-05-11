package br.com.hs.nfe.entity;

public class Estabelecimento implements java.io.Serializable {
	
	private static final long serialVersionUID = -3557801465452373827L;

	private int codEstabelecimento;
	private String enviNFeTXT = "";
	private String consReciNFeTXT = "";
	private String enviNFeXML = "";
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
	private String compactarXML = "";
	private String xmlCompactado = "";
	private String pe = "";
	private String enviNFeThreadSleep = "";
	private String corpoEmail = "";
	private String numeroCopias = "";
	private String cancNFeTXT = "";
	private String cancNFeXML = "";
	private String cancNFeXMLAssinados = "";
	private String cancNFeXMLValidos = "";
	private String cancNFeXMLInvalidos = "";
	private String cancNFeXMLAutorizados = "";
	private String cancNFeXMLDenegados = "";
	private String retCancNFeTXT = "";
	private String cancNFeXMLProcessados = "";
	private String danfeCanc = "";
	private String inutNFeTXT = "";
	private String inutNFeXML = "";
	private String inutNFeXMLAssinados = "";
	private String inutNFeXMLValidos = "";
	private String inutNFeXMLInvalidos = "";
	private String inutNFeXMLAutorizados = "";
	private String inutNFeXMLDenegados = "";
	private String retInutNFeTXT = "";
	private String descricao = "";
	private String assuntoEmailCancelamento = "";
	private String corpoEmailCancelamento = "";
	private String envDpecXML = "";
	private String envDpecXMLAssinados = "";
	private String envDpecXMLValidos = "";
	private String envDpecXMLInvalidos = "";
	private String envDpecXMLAutorizados = "";
	private String envDpecXMLDenegados = "";
	private String envDpecXMLProcessados = "";
	private String envDpecXMLEnviados = "";
	private String envDpecXMLCompactados = "";
	private String envDpecDanfe = "";
	private String envFsdaDanfe = "";
	private String envFsdaXMLProcessados = "";
	private String envFsdaXMLCompactados = "";
	private Character ativarDpecAutomatico;
	private Character ativarFsdaAutomatico;
	private Character imprimirDanfeDpecReenvio;
	private Character imprimirDanfeFsdaReenvio;
	private String impressoraFsda = "";
	private Character orientacaoDanfe = 'R';
	private String urlRMI = "";
	private String envEventoTXT = "";
	private String envEventoXML = "";
	private String envEventoXMLAssinados = "";
	private String envEventoXMLValidos = "";
	private String envEventoXMLInvalidos = "";
	private String envEventoXMLVinculado = "";
	private String envEventoXMLRejeitado = "";
	private String retEnvEventoTXT = "";
	private String razaoSocial = "";
	private Character imprimirFSDAManualmente;
	private String tipoCertificado = "";
	
	public Estabelecimento()
	{
		
	}
	
	public Estabelecimento(int codEstabelecimento)
	{
		this.codEstabelecimento = codEstabelecimento;
	}

	public Estabelecimento(String assuntoEmail, String cnpj,
			int codEstabelecimento, String compactarXML, String consReciNFeTXT,
			String danfe, String emailDe, String emailPara, String enviNFeTXT,
			String enviNFeXML, String enviNFeXMLAssinados,
			String enviNFeXMLAutorizados, String enviNFeXMLDenegados,
			String enviNFeXMLEnviados, String enviNFeXMLInvalidos,
			String enviNFeXMLProcessados, String enviNFeXMLValidos,
			String enviarEmail, String gerarDanfe,
			String impressora, String imprimirDanfe, String jks,
			String jksPassword, String logoDanfe, String pfxAlias,
			String pfxPassword, String portaEmail, String senhaEmail,
			String servidorEmail, String usuarioEmail,
			String xmlCompactado,String pe,String enviNFeThreadSleep,
			String corpoEmail,String numeroCopias,String cancNFeXMLAssinados,
			String cancNFeTXT,String cancNFeXML,
			String cancNFeXMLValidos,String cancNFeXMLInvalidos,
			String cancNFeXMLAutorizados,String cancNFeXMLDenegados,
			String retCancNFeTXT,String cancNFeXMLProcessados,String danfeCanc,
			String inutNFeTXT,String inutNFeXML,String inutNFeXMLAssinados,
			String inutNFeXMLValidos,String inutNFeXMLInvalidos,
			String inutNFeXMLAutorizados,String inutNFeXMLDenegados,
			String retInutNFeTXT,String descricao,String assuntoEmailCancelamento,
			String corpoEmailCancelamento,String envDpecXML, String envDpecXMLAssinados,
			String envDpecXMLValidos,String envDpecXMLInvalidos,String envDpecXMLAutorizados,
			String envDpecXMLDenegados,String envDpecXMLProcessados,
			String envDpecXMLEnviados,String envDpecXMLCompactados,String envDpecDanfe,
			String envFsdaDanfe,String envFsdaXMLCompactados,String envFsdaXMLProcessados,
			Character ativarDpecAutomatico,Character ativarFsdaAutomatico,
			Character imprimirDanfeDpecReenvio,Character imprimirDanfeFsdaReenvio,
			String impressoraFsda,Character orientacaoDanfe,String urlRMI,
			String envEventoTXT,String envEventoXML,String envEventoXMLAssinados,
			String envEventoXMLValidos, String envEventoXMLInvalidos,
			String envEventoXMLVinculado,String envEventoXMLRejeitado,
			String retEnvEventoTXT,String razaoSocial,Character imprimirFSDAManualmente,
			String tipoCertificado) {
		super();
		this.assuntoEmail = assuntoEmail;
		this.cnpj = cnpj;
		this.codEstabelecimento = codEstabelecimento;
		this.compactarXML = compactarXML;
		this.consReciNFeTXT = consReciNFeTXT;
		this.danfe = danfe;
		this.emailDe = emailDe;
		this.emailPara = emailPara;
		this.enviNFeTXT = enviNFeTXT;
		this.enviNFeXML = enviNFeXML;
		this.enviNFeXMLAssinados = enviNFeXMLAssinados;
		this.enviNFeXMLAutorizados = enviNFeXMLAutorizados;
		this.enviNFeXMLDenegados = enviNFeXMLDenegados;
		this.enviNFeXMLEnviados = enviNFeXMLEnviados;
		this.enviNFeXMLInvalidos = enviNFeXMLInvalidos;
		this.enviNFeXMLProcessados = enviNFeXMLProcessados;
		this.enviNFeXMLValidos = enviNFeXMLValidos;
		this.enviarEmail = enviarEmail;
		this.gerarDanfe = gerarDanfe;
		this.impressora = impressora;
		this.imprimirDanfe = imprimirDanfe;
		this.jks = jks;
		this.jksPassword = jksPassword;
		this.logoDanfe = logoDanfe;
		this.pfxAlias = pfxAlias;
		this.pfxPassword = pfxPassword;
		this.portaEmail = portaEmail;
		this.senhaEmail = senhaEmail;
		this.servidorEmail = servidorEmail;
		this.usuarioEmail = usuarioEmail;
		this.xmlCompactado = xmlCompactado;
		this.pe = pe;
		this.enviNFeThreadSleep = enviNFeThreadSleep;
		this.corpoEmail = corpoEmail;
		this.numeroCopias = numeroCopias;
		this.cancNFeTXT = cancNFeTXT;
		this.cancNFeXML = cancNFeXML;
		this.cancNFeXMLAssinados = cancNFeXMLAssinados;
		this.cancNFeXMLValidos = cancNFeXMLValidos;
		this.cancNFeXMLInvalidos = cancNFeXMLInvalidos;
		this.cancNFeXMLAutorizados = cancNFeXMLAutorizados;
		this.cancNFeXMLDenegados = cancNFeXMLDenegados;
		this.retCancNFeTXT = retCancNFeTXT;
		this.cancNFeXMLProcessados = cancNFeXMLProcessados;
		this.danfeCanc = danfeCanc;
		this.inutNFeTXT = inutNFeTXT;
		this.inutNFeXML = inutNFeXML;
		this.inutNFeXMLAssinados = inutNFeXMLAssinados;
		this.inutNFeXMLValidos = inutNFeXMLValidos;
		this.inutNFeXMLInvalidos = inutNFeXMLInvalidos;
		this.inutNFeXMLAutorizados = inutNFeXMLAutorizados;
		this.inutNFeXMLDenegados = inutNFeXMLDenegados;
		this.retInutNFeTXT = retInutNFeTXT;
		this.descricao = descricao;
		this.assuntoEmailCancelamento = assuntoEmailCancelamento;
		this.corpoEmailCancelamento = corpoEmailCancelamento;
		this.envDpecXML = envDpecXML;
		this.envDpecXMLAssinados = envDpecXMLAssinados;
		this.envDpecXMLValidos = envDpecXMLValidos;
		this.envDpecXMLInvalidos = envDpecXMLInvalidos;
		this.envDpecXMLAutorizados = envDpecXMLAutorizados;
		this.envDpecXMLDenegados = envDpecXMLDenegados;
		this.envDpecXMLProcessados = envDpecXMLProcessados;
		this.envDpecXMLEnviados = envDpecXMLEnviados;
		this.envDpecXMLCompactados = envDpecXMLCompactados;
		this.envDpecDanfe = envDpecDanfe;
		this.envFsdaDanfe = envFsdaDanfe;
		this.envFsdaXMLCompactados = envFsdaXMLCompactados;
		this.envFsdaXMLProcessados = envFsdaXMLProcessados;
		this.ativarDpecAutomatico = ativarDpecAutomatico;
		this.ativarFsdaAutomatico = ativarFsdaAutomatico;
		this.imprimirDanfeDpecReenvio = imprimirDanfeDpecReenvio;
		this.imprimirDanfeFsdaReenvio = imprimirDanfeFsdaReenvio;
		this.impressoraFsda = impressoraFsda;
		this.orientacaoDanfe = orientacaoDanfe;
		this.urlRMI = urlRMI;
		this.envEventoTXT = envEventoTXT;
		this.envEventoXML = envEventoXML;
		this.envEventoXMLAssinados = envEventoXMLAssinados;
		this.envEventoXMLValidos = envEventoXMLValidos;
		this.envEventoXMLInvalidos = envEventoXMLInvalidos;
		this.envEventoXMLVinculado = envEventoXMLVinculado;
		this.envEventoXMLRejeitado = envEventoXMLRejeitado;
		this.retEnvEventoTXT = retEnvEventoTXT;
		this.razaoSocial = razaoSocial;
		this.imprimirFSDAManualmente = imprimirFSDAManualmente;
		this.tipoCertificado = tipoCertificado;
	}

	public int getCodEstabelecimento() {
		return codEstabelecimento;
	}

	public void setCodEstabelecimento(int codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	public String getEnviNFeTXT() {
		return enviNFeTXT;
	}

	public void setEnviNFeTXT(String enviNFeTXT) {
		this.enviNFeTXT = enviNFeTXT;
	}

	public String getConsReciNFeTXT() {
		return consReciNFeTXT;
	}

	public void setConsReciNFeTXT(String consReciNFeTXT) {
		this.consReciNFeTXT = consReciNFeTXT;
	}

	public String getEnviNFeXML() {
		return enviNFeXML;
	}

	public void setEnviNFeXML(String enviNFeXML) {
		this.enviNFeXML = enviNFeXML;
	}

	public String getEnviNFeXMLAssinados() {
		return enviNFeXMLAssinados;
	}

	public void setEnviNFeXMLAssinados(String enviNFeXMLAssinados) {
		this.enviNFeXMLAssinados = enviNFeXMLAssinados;
	}

	public String getEnviNFeXMLValidos() {
		return enviNFeXMLValidos;
	}

	public void setEnviNFeXMLValidos(String enviNFeXMLValidos) {
		this.enviNFeXMLValidos = enviNFeXMLValidos;
	}

	public String getEnviNFeXMLInvalidos() {
		return enviNFeXMLInvalidos;
	}

	public void setEnviNFeXMLInvalidos(String enviNFeXMLInvalidos) {
		this.enviNFeXMLInvalidos = enviNFeXMLInvalidos;
	}

	public String getEnviNFeXMLEnviados() {
		return enviNFeXMLEnviados;
	}

	public void setEnviNFeXMLEnviados(String enviNFeXMLEnviados) {
		this.enviNFeXMLEnviados = enviNFeXMLEnviados;
	}

	public String getEnviNFeXMLAutorizados() {
		return enviNFeXMLAutorizados;
	}

	public void setEnviNFeXMLAutorizados(String enviNFeXMLAutorizados) {
		this.enviNFeXMLAutorizados = enviNFeXMLAutorizados;
	}

	public String getEnviNFeXMLDenegados() {
		return enviNFeXMLDenegados;
	}

	public void setEnviNFeXMLDenegados(String enviNFeXMLDenegados) {
		this.enviNFeXMLDenegados = enviNFeXMLDenegados;
	}

	public String getEnviNFeXMLProcessados() {
		return enviNFeXMLProcessados;
	}

	public void setEnviNFeXMLProcessados(String enviNFeXMLProcessados) {
		this.enviNFeXMLProcessados = enviNFeXMLProcessados;
	}

	public String getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(String enviarEmail) {
		this.enviarEmail = enviarEmail;
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

	public String getAssuntoEmail() {
		return assuntoEmail;
	}

	public void setAssuntoEmail(String assuntoEmail) {
		this.assuntoEmail = assuntoEmail;
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

	public String getJks() {
		return jks;
	}

	public void setJks(String jks) {
		this.jks = jks;
	}

	public String getJksPassword() {
		return jksPassword;
	}

	public void setJksPassword(String jksPassword) {
		this.jksPassword = jksPassword;
	}

	public String getPfxPassword() {
		return pfxPassword;
	}

	public void setPfxPassword(String pfxPassword) {
		this.pfxPassword = pfxPassword;
	}

	public String getPfxAlias() {
		return pfxAlias;
	}

	public void setPfxAlias(String pfxAlias) {
		this.pfxAlias = pfxAlias;
	}

	public String getGerarDanfe() {
		return gerarDanfe;
	}

	public void setGerarDanfe(String gerarDanfe) {
		this.gerarDanfe = gerarDanfe;
	}

	public String getDanfe() {
		return danfe;
	}

	public void setDanfe(String danfe) {
		this.danfe = danfe;
	}

	public String getImprimirDanfe() {
		return imprimirDanfe;
	}

	public void setImprimirDanfe(String imprimirDanfe) {
		this.imprimirDanfe = imprimirDanfe;
	}

	public String getImpressora() {
		return impressora;
	}

	public void setImpressora(String impressora) {
		this.impressora = impressora;
	}

	public String getLogoDanfe() {
		return logoDanfe;
	}

	public void setLogoDanfe(String logoDanfe) {
		this.logoDanfe = logoDanfe;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompactarXML() {
		return compactarXML;
	}

	public void setCompactarXML(String compactarXML) {
		this.compactarXML = compactarXML;
	}

	public String getXmlCompactado() {
		return xmlCompactado;
	}

	public void setXmlCompactado(String xmlCompactado) {
		this.xmlCompactado = xmlCompactado;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getEnviNFeThreadSleep() {
		return enviNFeThreadSleep;
	}

	public void setEnviNFeThreadSleep(String enviNFeThreadSleep) {
		this.enviNFeThreadSleep = enviNFeThreadSleep;
	}

	public String getCorpoEmail() {
		return corpoEmail;
	}

	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}

	public String getNumeroCopias() {
		return numeroCopias;
	}

	public void setNumeroCopias(String numeroCopias) {
		this.numeroCopias = numeroCopias;
	}

	public String getCancNFeTXT() {
		return cancNFeTXT;
	}

	public void setCancNFeTXT(String cancNFeTXT) {
		this.cancNFeTXT = cancNFeTXT;
	}

	public String getCancNFeXML() {
		return cancNFeXML;
	}

	public void setCancNFeXML(String cancNFeXML) {
		this.cancNFeXML = cancNFeXML;
	}

	public String getCancNFeXMLAssinados() {
		return cancNFeXMLAssinados;
	}

	public void setCancNFeXMLAssinados(String cancNFeXMLAssinados) {
		this.cancNFeXMLAssinados = cancNFeXMLAssinados;
	}

	public String getCancNFeXMLValidos() {
		return cancNFeXMLValidos;
	}

	public void setCancNFeXMLValidos(String cancNFeXMLValidos) {
		this.cancNFeXMLValidos = cancNFeXMLValidos;
	}

	public String getCancNFeXMLInvalidos() {
		return cancNFeXMLInvalidos;
	}

	public void setCancNFeXMLInvalidos(String cancNFeXMLInvalidos) {
		this.cancNFeXMLInvalidos = cancNFeXMLInvalidos;
	}

	public String getCancNFeXMLAutorizados() {
		return cancNFeXMLAutorizados;
	}

	public void setCancNFeXMLAutorizados(String cancNFeXMLAutorizados) {
		this.cancNFeXMLAutorizados = cancNFeXMLAutorizados;
	}

	public String getCancNFeXMLDenegados() {
		return cancNFeXMLDenegados;
	}

	public void setCancNFeXMLDenegados(String cancNFeXMLDenegados) {
		this.cancNFeXMLDenegados = cancNFeXMLDenegados;
	}

	public String getRetCancNFeTXT() {
		return retCancNFeTXT;
	}

	public void setRetCancNFeTXT(String retCancNFeTXT) {
		this.retCancNFeTXT = retCancNFeTXT;
	}

	public String getCancNFeXMLProcessados() {
		return cancNFeXMLProcessados;
	}

	public void setCancNFeXMLProcessados(String cancNFeXMLProcessados) {
		this.cancNFeXMLProcessados = cancNFeXMLProcessados;
	}

	public String getDanfeCanc() {
		return danfeCanc;
	}

	public void setDanfeCanc(String danfeCanc) {
		this.danfeCanc = danfeCanc;
	}

	public String getInutNFeTXT() {
		return inutNFeTXT;
	}

	public void setInutNFeTXT(String inutNFeTXT) {
		this.inutNFeTXT = inutNFeTXT;
	}

	public String getInutNFeXML() {
		return inutNFeXML;
	}

	public void setInutNFeXML(String inutNFeXML) {
		this.inutNFeXML = inutNFeXML;
	}

	public String getInutNFeXMLAssinados() {
		return inutNFeXMLAssinados;
	}

	public void setInutNFeXMLAssinados(String inutNFeXMLAssinados) {
		this.inutNFeXMLAssinados = inutNFeXMLAssinados;
	}

	public String getInutNFeXMLValidos() {
		return inutNFeXMLValidos;
	}

	public void setInutNFeXMLValidos(String inutNFeXMLValidos) {
		this.inutNFeXMLValidos = inutNFeXMLValidos;
	}

	public String getInutNFeXMLInvalidos() {
		return inutNFeXMLInvalidos;
	}

	public void setInutNFeXMLInvalidos(String inutNFeXMLInvalidos) {
		this.inutNFeXMLInvalidos = inutNFeXMLInvalidos;
	}

	public String getInutNFeXMLAutorizados() {
		return inutNFeXMLAutorizados;
	}

	public void setInutNFeXMLAutorizados(String inutNFeXMLAutorizados) {
		this.inutNFeXMLAutorizados = inutNFeXMLAutorizados;
	}

	public String getInutNFeXMLDenegados() {
		return inutNFeXMLDenegados;
	}

	public void setInutNFeXMLDenegados(String inutNFeXMLDenegados) {
		this.inutNFeXMLDenegados = inutNFeXMLDenegados;
	}

	public String getRetInutNFeTXT() {
		return retInutNFeTXT;
	}

	public void setRetInutNFeTXT(String retInutNFeTXT) {
		this.retInutNFeTXT = retInutNFeTXT;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAssuntoEmailCancelamento() {
		return assuntoEmailCancelamento;
	}

	public void setAssuntoEmailCancelamento(String assuntoEmailCancelamento) {
		this.assuntoEmailCancelamento = assuntoEmailCancelamento;
	}

	public String getCorpoEmailCancelamento() {
		return corpoEmailCancelamento;
	}

	public void setCorpoEmailCancelamento(String corpoEmailCancelamento) {
		this.corpoEmailCancelamento = corpoEmailCancelamento;
	}

	public String getEnvDpecXML() {
		return envDpecXML;
	}

	public void setEnvDpecXML(String envDpecXML) {
		this.envDpecXML = envDpecXML;
	}

	public String getEnvDpecXMLAssinados() {
		return envDpecXMLAssinados;
	}

	public void setEnvDpecXMLAssinados(String envDpecXMLAssinados) {
		this.envDpecXMLAssinados = envDpecXMLAssinados;
	}

	public String getEnvDpecXMLValidos() {
		return envDpecXMLValidos;
	}

	public void setEnvDpecXMLValidos(String envDpecXMLValidos) {
		this.envDpecXMLValidos = envDpecXMLValidos;
	}

	public String getEnvDpecXMLInvalidos() {
		return envDpecXMLInvalidos;
	}

	public void setEnvDpecXMLInvalidos(String envDpecXMLInvalidos) {
		this.envDpecXMLInvalidos = envDpecXMLInvalidos;
	}

	public String getEnvDpecXMLAutorizados() {
		return envDpecXMLAutorizados;
	}

	public void setEnvDpecXMLAutorizados(String envDpecXMLAutorizados) {
		this.envDpecXMLAutorizados = envDpecXMLAutorizados;
	}

	public String getEnvDpecXMLDenegados() {
		return envDpecXMLDenegados;
	}

	public void setEnvDpecXMLDenegados(String envDpecXMLDenegados) {
		this.envDpecXMLDenegados = envDpecXMLDenegados;
	}

	public String getEnvDpecXMLProcessados() {
		return envDpecXMLProcessados;
	}

	public void setEnvDpecXMLProcessados(String envDpecXMLProcessados) {
		this.envDpecXMLProcessados = envDpecXMLProcessados;
	}

	public String getEnvDpecXMLEnviados() {
		return envDpecXMLEnviados;
	}

	public void setEnvDpecXMLEnviados(String envDpecXMLEnviados) {
		this.envDpecXMLEnviados = envDpecXMLEnviados;
	}

	public String getEnvDpecXMLCompactados() {
		return envDpecXMLCompactados;
	}

	public void setEnvDpecXMLCompactados(String envDpecXMLCompactados) {
		this.envDpecXMLCompactados = envDpecXMLCompactados;
	}

	public String getEnvDpecDanfe() {
		return envDpecDanfe;
	}

	public void setEnvDpecDanfe(String envDpecDanfe) {
		this.envDpecDanfe = envDpecDanfe;
	}

	public String getEnvFsdaDanfe() {
		return envFsdaDanfe;
	}

	public void setEnvFsdaDanfe(String envFsdaDanfe) {
		this.envFsdaDanfe = envFsdaDanfe;
	}
	
	public String getEnvFsdaXMLProcessados() {
		return envFsdaXMLProcessados;
	}

	public void setEnvFsdaXMLProcessados(String envFsdaXMLProcessados) {
		this.envFsdaXMLProcessados = envFsdaXMLProcessados;
	}

	public String getEnvFsdaXMLCompactados() {
		return envFsdaXMLCompactados;
	}

	public void setEnvFsdaXMLCompactados(String envFsdaXMLCompactados) {
		this.envFsdaXMLCompactados = envFsdaXMLCompactados;
	}

	public Character getAtivarDpecAutomatico() {
		return ativarDpecAutomatico;
	}

	public void setAtivarDpecAutomatico(Character ativarDpecAutomatico) {
		this.ativarDpecAutomatico = ativarDpecAutomatico;
	}

	public Character getAtivarFsdaAutomatico() {
		return ativarFsdaAutomatico;
	}

	public void setAtivarFsdaAutomatico(Character ativarFsdaAutomatico) {
		this.ativarFsdaAutomatico = ativarFsdaAutomatico;
	}

	public Character getImprimirDanfeDpecReenvio() {
		return imprimirDanfeDpecReenvio;
	}

	public void setImprimirDanfeDpecReenvio(Character imprimirDanfeDpecReenvio) {
		this.imprimirDanfeDpecReenvio = imprimirDanfeDpecReenvio;
	}

	public Character getImprimirDanfeFsdaReenvio() {
		return imprimirDanfeFsdaReenvio;
	}

	public void setImprimirDanfeFsdaReenvio(Character imprimirDanfeFsdaReenvio) {
		this.imprimirDanfeFsdaReenvio = imprimirDanfeFsdaReenvio;
	}

	public String getImpressoraFsda() {
		return impressoraFsda;
	}

	public void setImpressoraFsda(String impressoraFsda) {
		this.impressoraFsda = impressoraFsda;
	}

	public Character getOrientacaoDanfe() {
		return orientacaoDanfe;
	}

	public void setOrientacaoDanfe(Character orientacaoDanfe) {
		this.orientacaoDanfe = orientacaoDanfe;
	}

	public String getUrlRMI() {
		return urlRMI;
	}

	public void setUrlRMI(String urlRMI) {
		this.urlRMI = urlRMI;
	}

	public String getEnvEventoTXT() {
		return envEventoTXT;
	}

	public void setEnvEventoTXT(String envEventoTXT) {
		this.envEventoTXT = envEventoTXT;
	}

	public String getEnvEventoXML() {
		return envEventoXML;
	}

	public void setEnvEventoXML(String envEventoXML) {
		this.envEventoXML = envEventoXML;
	}

	public String getEnvEventoXMLAssinados() {
		return envEventoXMLAssinados;
	}

	public void setEnvEventoXMLAssinados(String envEventoXMLAssinados) {
		this.envEventoXMLAssinados = envEventoXMLAssinados;
	}

	public String getEnvEventoXMLValidos() {
		return envEventoXMLValidos;
	}

	public void setEnvEventoXMLValidos(String envEventoXMLValidos) {
		this.envEventoXMLValidos = envEventoXMLValidos;
	}

	public String getEnvEventoXMLInvalidos() {
		return envEventoXMLInvalidos;
	}

	public void setEnvEventoXMLInvalidos(String envEventoXMLInvalidos) {
		this.envEventoXMLInvalidos = envEventoXMLInvalidos;
	}

	public String getEnvEventoXMLVinculado() {
		return envEventoXMLVinculado;
	}

	public void setEnvEventoXMLVinculado(String envEventoXMLVinculado) {
		this.envEventoXMLVinculado = envEventoXMLVinculado;
	}

	public String getEnvEventoXMLRejeitado() {
		return envEventoXMLRejeitado;
	}

	public void setEnvEventoXMLRejeitado(String envEventoXMLRejeitado) {
		this.envEventoXMLRejeitado = envEventoXMLRejeitado;
	}
	
	public String getRetEnvEventoTXT() {
		return retEnvEventoTXT;
	}

	public void setRetEnvEventoTXT(String retEnvEventoTXT) {
		this.retEnvEventoTXT = retEnvEventoTXT;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Character getImprimirFSDAManualmente() {
		return imprimirFSDAManualmente;
	}

	public void setImprimirFSDAManualmente(Character imprimirFSDAManualmente) {
		this.imprimirFSDAManualmente = imprimirFSDAManualmente;
	}

	public String getTipoCertificado() {
		return tipoCertificado;
	}

	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}		
	
}
