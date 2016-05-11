package br.com.hs.nfe.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Cte;
import br.com.hs.nfe.filtro.CteFiltro;

public class CteDAO extends GenericDAO<Cte> {
	
	private static final Logger logger = Logger.getLogger("CteDAO");
	
	private Vector<Object> parametros;
	
	@SuppressWarnings("unchecked")
	public List<Cte> procuraCTes(String cnpj)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando CTE do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("cte.procuraCTes");
			query.setString("_cnpj", cnpj);
			//TODO: se começar a dar pau de memória limitar q quantidade de registros
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	public Long countByParametros(CteFiltro ctf)
	{
		StringBuffer sql = new StringBuffer();
		
		if (ctf == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		sql.append(" select count(ct) from Cte ct where 1=1 ");
		sql.append(setFiltros(ctf));
		
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
	@SuppressWarnings("unchecked")
	public List<Cte> findByParametros(CteFiltro ctf)
	{
		if (ctf == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT ct FROM Cte ct WHERE 1=1 ");
		sql.append(setFiltros(ctf));
		sql.append(" ORDER BY ");
		sql.append(" ct.dhRecbto DESC");
		
		try {
			logger.debug(sql.toString());
			// session.createQuery() //Atribuir linha SQL
			Query query = session.createQuery(sql.toString());
			
			if (ctf.getStart() != null) {
				query.setFirstResult(ctf.getStart());
			}
			
			if (ctf.getMaxResults() != null) {
				query.setMaxResults(ctf.getMaxResults());
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
	private String setFiltros(CteFiltro ctf) throws IllegalArgumentException {
		StringBuffer sql = new StringBuffer();
		
		parametros = new Vector<Object>();
		
		/*
		 * Data de Emissão
		 */
		if (ctf.getDhRecbtoInicial() != null) {
			sql.append(" AND ct.dhRecbto >= ? ");
			parametros.add(ctf.getDhRecbtoInicial());
		}
		
		if (ctf.getDhRecbtoFinal() != null) {
			sql.append(" AND ct.dhRecbto <= ? ");
			parametros.add(ctf.getDhRecbtoFinal());
		}
		
		if (ctf.getDest() != null)
		{
			sql.append(" AND ct.dest LIKE '%").append(ctf.getDest()).append("%'");
			//parametros.add(ctf.getDest());
		}
		
		/*
		 * Numero Nota Fiscal
		 */
		if (ctf.getNCTInicial() != null) {
			sql.append(" AND ct.nct >= ? ");
			parametros.add(ctf.getNCTInicial());
		}
		
		if (ctf.getNCTFinal() != null) {
			sql.append(" AND ct.nct <= ? ");
			parametros.add(ctf.getNCTFinal());
		}
		
		/*
		 * Serie
		 */
		if (ctf.getSerieInicial() != null) {
			sql.append(" AND ct.serie >= ? ");
			parametros.add(ctf.getSerieInicial());
		}
		
		if (ctf.getSerieFinal() != null) {
			sql.append(" AND ct.serie <= ? ");
			parametros.add(ctf.getSerieFinal());
		}
		
		/*
		 * Chave Acesso
		 */
		if (ctf.getChaveAcesso() != null) {
			sql.append(" AND ct.chaveAcesso = ? ");
			parametros.add(ctf.getChaveAcesso());
		}
		
		/*
		 * Status
		 */
		if (ctf.getCstat().size()>0) {
			sql.append(" AND ct.cstat IN ( ");
			for(int i=0;i<ctf.getCstat().size();i++)
			{
				String cstat = ctf.getCstat().get(i);
				sql.append(" ? ");
				if(i!=ctf.getCstat().size()-1)
				{
					sql.append(",");
				}
				parametros.add(cstat);
			}
			sql.append(" ) ");
		}
		
		return sql.toString();
	}
}
