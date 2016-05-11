package br.com.hs.nfe.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.InutNfe;
import br.com.hs.nfe.filtro.InutilizaNotaFiscalFiltro;

public class InutNFeDAO extends GenericDAO<InutNfe> {
	private static final Logger logger = Logger.getLogger("InutNFeDAO");
	private Vector<Object> parametros;
	@SuppressWarnings("unchecked")
	public List<InutNfe> procuraInutilizacaoAssinada(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Inutilizações Assinadas do pe: [" + pe + "] do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("nfe.procuraInutilizacaoAssinada");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<InutNfe> procuraInutilizacoesGeradas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Inutilizações Geradas do pe: [" + pe + "] do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("nfe.procuraInutilizacoesGeradas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<InutNfe> procuraInutilizacaoValida(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Inutilizações Válidas do pe: [" + pe + "] do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("nfe.procuraInutilizacaoValida");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<InutNfe> procuraInutilizacaoRetornoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Inutilizações Autorizadas do pe: [" + pe + "] do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("nfe.procuraInutilizacaoRetornoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	private String setFiltros(InutilizaNotaFiscalFiltro nff) throws IllegalArgumentException {
		StringBuffer sql = new StringBuffer();
		
		parametros = new Vector<Object>();
		/*
		 * Data de Emissão
		 */
		if (nff.getDhRecbtoInicial() != null) {
			sql.append(" AND if.dhRecbto >= ? ");
			parametros.add(nff.getDhRecbtoInicial());
		}
		
		if (nff.getDhRecbtoFinal() != null) {
			sql.append(" AND if.dhRecbto <= ? ");
			parametros.add(nff.getDhRecbtoFinal());
		}
		return sql.toString();
	}
	@SuppressWarnings("unchecked")
	public List<InutNfe> findByParametros(InutilizaNotaFiscalFiltro nff)
	{
		if (nff == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select if from InutNfe if where 1=1 ");
		sql.append(setFiltros(nff));
		sql.append(" ORDER BY ");
		sql.append(" if.dhRecbto DESC");
		
		try {
			logger.debug(sql.toString());
			// session.createQuery() //Atribuir linha SQL
			Query query = session.createQuery(sql.toString());
			
			if (nff.getStart() != null) {
				query.setFirstResult(nff.getStart());
			}
			
			if (nff.getMaxResults() != null) {
				query.setMaxResults(nff.getMaxResults());
			}
			
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
	public Long countByParametros(InutilizaNotaFiscalFiltro iff)
	{
		StringBuffer sql = new StringBuffer();
		
		if (iff == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		sql.append(" select count(if) from InutNfe if where 1=1 ");
		sql.append(setFiltros(iff));
		
		try {
			logger.debug(sql.toString());
			// session.createQuery() //Atribuir linha SQL
			Query query = session.createQuery(sql.toString());
			
			transferParams(query, parametros);
			return (Long) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
	}
}
