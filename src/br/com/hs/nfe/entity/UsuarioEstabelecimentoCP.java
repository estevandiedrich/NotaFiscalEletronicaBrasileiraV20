package br.com.hs.nfe.entity;

// Generated 13/06/2008 11:47:43 by Hibernate Tools 3.2.2.GA

/**
 * UsuarioEstabelecimentoCP generated by hbm2java
 */
public class UsuarioEstabelecimentoCP implements java.io.Serializable {
	
	private static final long serialVersionUID = -8618253463793244079L;
	
	private int codUsuario;
	private int codEstabelecimento;
	
	public UsuarioEstabelecimentoCP() {
	}
	
	public UsuarioEstabelecimentoCP(int codUsuario, int codEstabelecimento) {
		this.codUsuario = codUsuario;
		this.codEstabelecimento = codEstabelecimento;
	}
	
	public int getCodUsuario() {
		return this.codUsuario;
	}
	
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	
	public int getCodEstabelecimento() {
		return this.codEstabelecimento;
	}
	
	public void setCodEstabelecimento(int codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}
	
}
