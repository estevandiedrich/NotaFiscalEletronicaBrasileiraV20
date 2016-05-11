package br.com.hs.nfe.conf;

import br.com.hs.nfe.dao.SistemaDAO;
import br.com.hs.nfe.entity.Sistema;

public class PropriedadesSistema {
	private static PropriedadesSistema me;
	public static SistemaDAO sistemaDAO;
	private Sistema sistema;
	private PropriedadesSistema()
	{
		sistemaDAO = new SistemaDAO();
	}
	public static synchronized PropriedadesSistema getInstance()
	{
		if(me == null)
		{
			me = new PropriedadesSistema();
			me.sistema = sistemaDAO.getSistema(); 
		}
		return me;
	}
	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
}
