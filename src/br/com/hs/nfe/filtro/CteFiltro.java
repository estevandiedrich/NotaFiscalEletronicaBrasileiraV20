package br.com.hs.nfe.filtro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CteFiltro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8874519603468255036L;
	
	
	private String nCTInicial;
	private String nCTFinal;
	private String serieInicial;
	private String serieFinal;
	private String chaveAcesso;
	private Date dhRecbtoInicial;
	private Date dhRecbtoFinal;
	private List<String> cstat;
	private List<Integer> codEstabelecimento;
	private String xmotivo;
	private String cnpj;
	private String dest;
	private String rem;
	
	private Integer start;
	
	private Integer maxResults;
	
	public String getNCTInicial() {
		return nCTInicial;
	}
	public void setNCTInicial(String inicial) {
		nCTInicial = inicial;
	}
	public String getNCTFinal() {
		return nCTFinal;
	}
	public void setNCTFinal(String final1) {
		nCTFinal = final1;
	}
	public String getSerieInicial() {
		return serieInicial;
	}
	public void setSerieInicial(String serieInicial) {
		this.serieInicial = serieInicial;
	}
	public String getSerieFinal() {
		return serieFinal;
	}
	public void setSerieFinal(String serieFinal) {
		this.serieFinal = serieFinal;
	}
	public String getChaveAcesso() {
		return chaveAcesso;
	}
	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}
	public Date getDhRecbtoInicial() {
		return dhRecbtoInicial;
	}
	public void setDhRecbtoInicial(Date dhRecbtoInicial) {
		this.dhRecbtoInicial = dhRecbtoInicial;
	}
	public Date getDhRecbtoFinal() {
		return dhRecbtoFinal;
	}
	public void setDhRecbtoFinal(Date dhRecbtoFinal) {
		this.dhRecbtoFinal = dhRecbtoFinal;
	}
	public List<String> getCstat() {
		return cstat;
	}
	public void setCstat(List<String> cstat) {
		this.cstat = cstat;
	}
	public List<Integer> getCodEstabelecimento() {
		return codEstabelecimento;
	}
	public void setCodEstabelecimento(List<Integer> codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}
	public String getXmotivo() {
		return xmotivo;
	}
	public void setXmotivo(String xmotivo) {
		this.xmotivo = xmotivo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
}
