package br.com.hs.nfe.conf;

import java.util.List;

import br.com.hs.nfe.dao.EstabelecimentoDAO;
import br.com.hs.nfe.entity.Estabelecimento;

public class Estabelecs {
	private static Estabelecs me;
	private static EstabelecimentoDAO estabDAO;
	public List<Estabelecimento> estabelecs;
	public List<Estabelecimento> pes;
	private Estabelecs()
	{
		estabDAO = new EstabelecimentoDAO();
	}
	public synchronized static Estabelecs getInstance()
	{
		if(me == null)
		{
			me = new Estabelecs();
			me.pes = estabDAO.findAll();
			me.estabelecs = estabDAO.getEstabelecimentos();
		}
		return me;
	}
	public Estabelecimento getEstabelecimento(String cnpj)
	{
		for(Estabelecimento estabelecimento:estabelecs)
		{
			if(estabelecimento.getCnpj().equalsIgnoreCase(cnpj))
			{
				return estabelecimento;
			}
		}
		return null;
	}
}
