package br.com.hs.nfe.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.entity.NfeEntrada;
import br.com.hs.nfe.filtro.NotaFiscalEntradaFiltro;
import br.com.hs.nfe.hb.NFeEntradaUpdateCommand;

public class NfeEntradaDAO extends GenericDAO<Nfe>{
	private static final Logger logger = Logger.getLogger("NfeEntradaDAO");
	private Vector<Object> parametros;
	public NfeEntradaDAO()
	{
		
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> procuraNotasEntrada(String cnpj)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas de entrada.");
		try {
			Query query = session.getNamedQuery("nfeentrada.procuraNotasEntrada");
			query.setString("_cnpj", cnpj);
			return (List<NfeEntrada>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> procuraEventosConfirmacaoPendentes(String cnpj)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas de entrada com confirmacao de recebimento pendentes.");
		try {
			Query query = session.getNamedQuery("nfeentrada.procuraEventosConfirmacaoPendentes");
			query.setString("_cnpj", cnpj);
			return (List<NfeEntrada>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> consultaEventos()
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas de entrada.");
		try {
			Query query = session.getNamedQuery("nfeentrada.consultaEventos");
			return (List<NfeEntrada>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	public NfeEntrada findByChaveAcesso(String chaveAcesso) {
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Entrada com chave de acesso: [" + chaveAcesso + "]");
		
		try {
			Query query = session.getNamedQuery("nfeentrada.findByChaveAcesso");
			query.setString("_chaveAcesso", chaveAcesso);
			return (NfeEntrada) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
	}
	public int solicitaEventosNotaFiscalEntrada(String chaveAcesso)
	{
		NfeEntrada nfe = this.findByChaveAcesso(chaveAcesso);
		nfe.setConsultaEventos('1');
		try
		{
			NFeEntradaUpdateCommand updateCommand = new NFeEntradaUpdateCommand(nfe);
			updateCommand.execute();
		}
		catch(Exception e)
		{
			return 0;
		}
		return 1;
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> procuraTxtEntradaPendente()
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Txt pendente de entrada.");
		try {
			Query query = session.getNamedQuery("nfeentrada.procuraTxtEntradaPendente");
			return (List<NfeEntrada>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> procuraNotasProcessadas()
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas de entrada processadas.");
		try {
			Query query = session.getNamedQuery("nfeentrada.procuraNotasProcessadas");
			return (List<NfeEntrada>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<NfeEntrada> findByParametros(NotaFiscalEntradaFiltro nff) {
		if (nff == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select nf from NfeEntrada nf,Estabelecimento e where nf.dest = e.cnpj");
		sql.append(setFiltros(nff));
		sql.append(" ORDER BY ");
		sql.append(" nf.dhRecbto DESC");
		
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
	private String setFiltros(NotaFiscalEntradaFiltro nff) throws IllegalArgumentException {
		StringBuffer sql = new StringBuffer();
		
		parametros = new Vector<Object>();
		
		/*
		 * Data de Emissão
		 */
		if (nff.getDhRecbtoInicial() != null) {
			sql.append(" AND nf.dhRecbto >= ? ");
			parametros.add(nff.getDhRecbtoInicial());
		}
		
		if (nff.getDhRecbtoFinal() != null) {
			sql.append(" AND nf.dhRecbto <= ? ");
			parametros.add(nff.getDhRecbtoFinal());
		}
		
		if (nff.getDest() != null)
		{
			sql.append(" AND nf.dest LIKE '%").append(nff.getDest()).append("%'");
			//parametros.add(nff.getDest());
		}
		
		if(nff.getCodEstabelecimento().size()>0)
		{
			sql.append(" AND e.codEstabelecimento IN (");
			for(int i=0;i<nff.getCodEstabelecimento().size();i++)
			{
				Integer codEstabelecimento = nff.getCodEstabelecimento().get(i);
				sql.append(" ? ");
				if(i<nff.getCodEstabelecimento().size()-1)
				{
					sql.append(",");
				}
				parametros.add(codEstabelecimento);
			}
			sql.append(" ) ");
		}
		
		/*
		 * Numero Nota Fiscal
		 */
		if (nff.getNNFInicial() != null) {
			sql.append(" AND nf.nnf >= ? ");
			parametros.add(nff.getNNFInicial());
		}
		
		if (nff.getNNFFinal() != null) {
			sql.append(" AND nf.nnf <= ? ");
			parametros.add(nff.getNNFFinal());
		}
		
		/*
		 * Serie
		 */
		if (nff.getSerieInicial() != null) {
			sql.append(" AND nf.serie >= ? ");
			parametros.add(nff.getSerieInicial());
		}
		
		if (nff.getSerieFinal() != null) {
			sql.append(" AND nf.serie <= ? ");
			parametros.add(nff.getSerieFinal());
		}
		
		/*
		 * Chave Acesso
		 */
		if (nff.getChaveAcesso() != null) {
			sql.append(" AND nf.chaveAcesso = ? ");
			parametros.add(nff.getChaveAcesso());
		}
		
		/*
		 * Status
		 */
		if (nff.getCstat().size()>0) {
			sql.append(" AND nf.cstat IN ( ");
			for(int i=0;i<nff.getCstat().size();i++)
			{
				String cstat = nff.getCstat().get(i);
				sql.append(" ? ");
				if(i!=nff.getCstat().size()-1)
				{
					sql.append(",");
				}
				parametros.add(cstat);
			}
			sql.append(" ) ");
		}
		
		return sql.toString();
	}
	public Long countByParamentros(NotaFiscalEntradaFiltro nff)
	{
		StringBuffer sql = new StringBuffer();
		
		if (nff == null) {
			throw new IllegalArgumentException("Não foram enviados parâmetros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		sql.append(" select count(nf) from NfeEntrada nf,Estabelecimento e where nf.dest = e.cnpj ");
		sql.append(setFiltros(nff));
		
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
