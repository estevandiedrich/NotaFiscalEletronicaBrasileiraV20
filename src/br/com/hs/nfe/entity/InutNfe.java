package br.com.hs.nfe.entity;

import java.io.Serializable;
import java.util.Date;

public class InutNfe implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5068276083894655777L;
	
	private int codInutilizaNotaFiscal;
	private String nnfIni;
	private String nnfFin;
	private String serie;
	private Date dhRecbto;
	private String cstat;
	private String xmotivo;
	private String xjust;
	private String pe;
	private String nprot;
	private String cnpj;
	private String ano;
	private Character xmlInutilizado;
	private String id;
	private int codEstabelecimento;
	private String cuf;
	private Character tpAmb;
	private Character tpEmis;
	public InutNfe()
	{
		
	}
	public InutNfe(int codInutilizaNotaFiscal, String nnfIni, String nnfFin,
			String serie, Date dhRecbto, String cstat, String xmotivo,
			String xjust, String pe, String nprot, String cnpj, String ano,
			Character xmlInutilizado,String id,int codEstabelecimento,
			String cuf,Character tpAmb,Character tpEmis) {
		super();
		this.codInutilizaNotaFiscal = codInutilizaNotaFiscal;
		this.nnfIni = nnfIni;
		this.nnfFin = nnfFin;
		this.serie = serie;
		this.dhRecbto = dhRecbto;
		this.cstat = cstat;
		this.xmotivo = xmotivo;
		this.xjust = xjust;
		this.pe = pe;
		this.nprot = nprot;
		this.cnpj = cnpj;
		this.ano = ano;
		this.xmlInutilizado = xmlInutilizado;
		this.id = id;
		this.codEstabelecimento = codEstabelecimento;
		this.cuf = cuf;
		this.tpAmb = tpAmb;
		this.tpEmis = tpEmis;
	}
	public int getCodInutilizaNotaFiscal() {
		return codInutilizaNotaFiscal;
	}
	public void setCodInutilizaNotaFiscal(int codInutilizaNotaFiscal) {
		this.codInutilizaNotaFiscal = codInutilizaNotaFiscal;
	}
	public String getNnfIni() {
		return nnfIni;
	}
	public void setNnfIni(String nnfIni) {
		this.nnfIni = nnfIni;
	}
	public String getNnfFin() {
		return nnfFin;
	}
	public void setNnfFin(String nnfFin) {
		this.nnfFin = nnfFin;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Date getDhRecbto() {
		return dhRecbto;
	}
	public void setDhRecbto(Date dhRecbto) {
		this.dhRecbto = dhRecbto;
	}
	public String getCstat() {
		return cstat;
	}
	public void setCstat(String cstat) {
		this.cstat = cstat;
	}
	public String getXmotivo() {
		return xmotivo;
	}
	public void setXmotivo(String xmotivo) {
		this.xmotivo = xmotivo;
	}
	public String getXjust() {
		return xjust;
	}
	public void setXjust(String xjust) {
		this.xjust = xjust;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	public String getNprot() {
		return nprot;
	}
	public void setNprot(String nprot) {
		this.nprot = nprot;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public Character getXmlInutilizado() {
		return xmlInutilizado;
	}
	public void setXmlInutilizado(Character xmlInutilizado) {
		this.xmlInutilizado = xmlInutilizado;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCodEstabelecimento() {
		return codEstabelecimento;
	}
	public void setCodEstabelecimento(int codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}
	public String getCuf() {
		return cuf;
	}
	public void setCuf(String cuf) {
		this.cuf = cuf;
	}
	public Character getTpAmb() {
		return tpAmb;
	}
	public void setTpAmb(Character tpAmb) {
		this.tpAmb = tpAmb;
	}
	public Character getTpEmis() {
		return tpEmis;
	}
	public void setTpEmis(Character tpEmis) {
		this.tpEmis = tpEmis;
	}
	
}
