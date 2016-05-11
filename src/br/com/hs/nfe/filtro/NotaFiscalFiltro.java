package br.com.hs.nfe.filtro;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Estevan Diedrich
 */
public class NotaFiscalFiltro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nNFInicial;
	private String nNFFinal;
	private String serieInicial;
	private String serieFinal;
	private String chaveAcesso;
	private Date dhRecbtoInicial;
	private Date dhRecbtoFinal;
	private List<String> cstat;
	private List<Integer> codEstabelecimento;
	private String xmotivo;
	private String cnpj;
	private String pe;
	private String dest;
	
	private Integer start;
	
	private Integer codProcesso = null;
	
	private Integer maxResults;
	
	private boolean sortByModificadoEmDesc = false;
	
	private boolean sortByTravar = false;
	
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
	
	public String toString() {
		String format = "[{0}] [{1}] [{2}] [{3}] [{4}] [{5}] [{6}] [{7}] [{8}] [{9}] [{10}] [{11}] [{12}] [{13}] [{14}] [{15}] [{16}] [{17}]";
		return MessageFormat.format(format, nNFInicial, nNFFinal, serieInicial, serieFinal, chaveAcesso, dhRecbtoInicial, dhRecbtoFinal, cstat, xmotivo,cnpj,pe, maxResults, sortByModificadoEmDesc, sortByTravar);
	}
	
	public Integer getMaxResults() {
		return maxResults;
	}
	
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	public boolean isSortByModificadoEmDesc() {
		return sortByModificadoEmDesc;
	}
	
	public void setSortByModificadoEmDesc(boolean sortByModificadoEmDesc) {
		this.sortByModificadoEmDesc = sortByModificadoEmDesc;
	}
	
	public Integer getCodProcesso() {
		return codProcesso;
	}
	
	public void setCodProcesso(Integer codProcesso) {
		this.codProcesso = codProcesso;
	}
	
	public boolean isSortByTravar() {
		return sortByTravar;
	}
	
	public void setSortByTravar(boolean sortByTravar) {
		this.sortByTravar = sortByTravar;
	}
	
	public Integer getStart() {
		return start;
	}
	
	public void setStart(Integer start) {
		this.start = start;
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

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public List<Integer> getCodEstabelecimento() {
		return codEstabelecimento;
	}

	public void setCodEstabelecimento(List<Integer> codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getnNFInicial() {
		return nNFInicial;
	}

	public void setnNFInicial(String nNFInicial) {
		this.nNFInicial = nNFInicial;
	}

	public String getnNFFinal() {
		return nNFFinal;
	}

	public void setnNFFinal(String nNFFinal) {
		this.nNFFinal = nNFFinal;
	}
	
}