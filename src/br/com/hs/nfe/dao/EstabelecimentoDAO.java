package br.com.hs.nfe.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Estabelecimento;

public class EstabelecimentoDAO extends GenericDAO<Estabelecimento> {
	public static void clearCache() {
	}
	private static final Logger logger = Logger.getLogger("EstabelecimentoDAO");
	public Estabelecimento findByCod(int cod) {
		Estabelecimento estab = null;
		if (estab == null) {
			Session session = getSession();
			try {
				session.beginTransaction();
				estab = (Estabelecimento) session.get(getTabela(), cod);
			} finally {
				session.getTransaction().commit();
			}
		}
		return estab;
	}
	@SuppressWarnings("unchecked")
	public List<Estabelecimento> getEstabelecimentos()
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Estabelecimentos.");
		try {
			Query query = session.getNamedQuery("estabelecimento.getEstabelecimentos");
			return (List<Estabelecimento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	public Estabelecimento findByCnpjPe(String cnpj,String pe) {
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Estabelecimento com cnpj: [" + cnpj + "] e pe ["+pe+"]");
		
		try {
			Query query = session.getNamedQuery("estabelecimento.findByCnpjPe");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (Estabelecimento) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Estabelecimento> findByCnpjPeDescricao(Estabelecimento estabelecimento)
	{
		Session session = getSession();
		Vector<Object> parametros = new Vector<Object>();
		session.beginTransaction();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select e from Estabelecimento e where 1=1 ");
		
		
		try {
			
//			if (nff.getStart() != null) {
//				query.setFirstResult(nff.getStart());
//			}
//			
//			if (nff.getMaxResults() != null) {
//				query.setMaxResults(nff.getMaxResults());
//			}
			if(!estabelecimento.getCnpj().equalsIgnoreCase(""))
			{
				sql.append(" and e.cnpj = ?");
				parametros.add(estabelecimento.getCnpj());
			}
			if(!estabelecimento.getPe().equalsIgnoreCase(""))
			{
				sql.append(" and e.pe = ?");
				parametros.add(estabelecimento.getPe());
			}
			if(!estabelecimento.getDescricao().equalsIgnoreCase(""))
			{
				sql.append(" and e.descricao = ?");
				parametros.add(estabelecimento.getDescricao());
			}
			logger.debug(sql.toString());
			Query query = session.createQuery(sql.toString());
			
			transferParams(query, parametros);
			return query.list();
		}
		catch(IllegalArgumentException e)
		{
			logger.error(e);
			return null;
		}
		finally {
			session.getTransaction().commit();
		}
	}
}
