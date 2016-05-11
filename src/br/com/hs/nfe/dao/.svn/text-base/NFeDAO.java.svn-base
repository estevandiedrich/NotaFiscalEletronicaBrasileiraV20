package br.com.hs.nfe.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Nfe;
import br.com.hs.nfe.filtro.NotaFiscalFiltro;
import br.com.hs.nfe.hb.NFeUpdateCommand;

public class NFeDAO extends GenericDAO<Nfe>{
	private static final Logger logger = Logger.getLogger("NFeDAO");
	
	private Vector<Object> parametros;
	
	/**
	 * Busca a Nota Fiscal por chave de acesso
	 * 
	 * @param chaveAcesso
	 *            Chave de acesso
	 * @return Nota Fiscal
	 */
	public Nfe findByChaveAcesso(String chaveAcesso) {
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal com chave de acesso: [" + chaveAcesso + "]");
		
		try {
			Query query = session.getNamedQuery("nfe.findByChaveAcesso");
			query.setString("_chaveAcesso", chaveAcesso);
			return (Nfe) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> findByCnpjPE(String cnpj,String pe) {
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas Fiscais do pe: [" + pe + "] do estabelecimento: ["+cnpj+"]");
		
		try {
			Query query = session.getNamedQuery("nfe.findByCnpjPE");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			query.setFirstResult(0);
			query.setMaxResults(100);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	
	private String setFiltros(NotaFiscalFiltro nff) throws IllegalArgumentException {
		StringBuffer sql = new StringBuffer();
		
		parametros = new Vector<Object>();
		
		if(nff.getCnpj() != null && !nff.getCnpj().equalsIgnoreCase(""))
		{
			sql.append(" AND nf.cnpj = ?");
			parametros.add(nff.getCnpj());
		}
		
		/*
		 * Data de Emiss�o
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
		
		if(nff.getCodEstabelecimento() != null && nff.getCodEstabelecimento().size()>0)
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
		if (nff.getnNFInicial() != null && !nff.getnNFInicial().equalsIgnoreCase("")) {
			sql.append(" AND nf.nnf >= ? ");
			parametros.add(Integer.valueOf(nff.getnNFInicial()));
		}
		
		if (nff.getnNFFinal() != null && !nff.getnNFFinal().equalsIgnoreCase("")) {
			sql.append(" AND nf.nnf <= ? ");
			parametros.add(Integer.valueOf(nff.getnNFFinal()));
		}
		
		/*
		 * Serie
		 */
		if (nff.getSerieInicial() != null && !nff.getSerieInicial().equalsIgnoreCase("")) {
			sql.append(" AND nf.serie >= ? ");
			parametros.add(Integer.valueOf(nff.getSerieInicial()));
		}
		
		if (nff.getSerieFinal() != null && !nff.getSerieFinal().equalsIgnoreCase("")) {
			sql.append(" AND nf.serie <= ? ");
			parametros.add(Integer.valueOf(nff.getSerieFinal()));
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
		if (nff.getCstat() != null && nff.getCstat().size()>0) {
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
	
	@SuppressWarnings("unchecked")
	public List<Nfe> findByParametros(NotaFiscalFiltro nff) {
		if (nff == null) {
			throw new IllegalArgumentException("N�o foram enviados par�metros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select nf from Nfe nf,Estabelecimento e where nf.cnpj = e.cnpj and nf.pe = e.pe ");
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
	
	public Long countByParametros(NotaFiscalFiltro nff) throws IllegalArgumentException {
		StringBuffer sql = new StringBuffer();
		
		if (nff == null) {
			throw new IllegalArgumentException("N�o foram enviados par�metros");
		}
		
		Session session = getSession();
		session.beginTransaction();
		
		sql.append(" select count(nf) from Nfe nf,Estabelecimento e where nf.cnpj = e.cnpj and nf.pe = e.pe ");
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

	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasGeradas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Gerada.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasGeradas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasAssinadas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Assinada.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasAssinadas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasValidas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Validas.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasValidas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasEnviadas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Validas.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasEnviadas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasAutorizadas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Validas.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasAutorizadas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasDuplicadas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Duplicadas.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasDuplicadas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> consultaEventos(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Consultando eventos.");
		try {
			Query query = session.getNamedQuery("nfe.consultaEventos");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasProcessadas(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal Processadas.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasProcessadas");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDanfeGerado(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Danfes gerados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDanfeGerado");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraEmailsEnviados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Emais enviados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraEmailsEnviados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraXMLRetornoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Retornos pendentes.");
		try {
			Query query = session.getNamedQuery("nfe.procuraXMLRetornoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCompactacaoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Compactacoes pendentes.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCompactacaoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraPersistenciaPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Persistencia pendentes.");
		try {
			Query query = session.getNamedQuery("nfe.procuraPersistenciaPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos pendentes.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentoAssinado(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos Assinados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentoAssinado");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasCancelamento(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos pendentes.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasCancelamento");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentoValido(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos Validos.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentoValido");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentoRetornoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando retornos pendentes de Cancelamentos.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentoRetornoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentoAutorizado(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos Autorizados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentoAutorizado");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCancelamentosProcessados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Cancelamentos Processados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCancelamentosProcessados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpec(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando DPEC.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpec");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecGerados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando DPEC Gerados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecGerados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecAssinados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando DPEC Assinados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecAssinados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecValidos(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando DPEC Validos.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecValidos");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecEnviados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando DPEC Enviados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecEnviados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasReenviar(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Notas para reenvio.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasReenviar");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecAutorizados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec autorizados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecAutorizados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCompactacaoPendenteDpec(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec Descompactados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCompactacaoPendenteDpec");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecProcessados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec Descompactados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecProcessados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecEmailPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec Email pendente.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecEmailPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraFsda(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda.");
		try {
			Query query = session.getNamedQuery("nfe.procuraFsda");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraFsdaPreparados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda preparados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraFsdaPreparados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraFsdaProcessados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda processados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraFsdaProcessados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraNotasReenviarFsda(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda processados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraNotasReenviarFsda");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraCompactacaoPendenteFsda(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando FSDA Descompactados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraCompactacaoPendenteFsda");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraDpecRetornoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec retorno pendente.");
		try {
			Query query = session.getNamedQuery("nfe.procuraDpecRetornoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraFsdaDanfeGerados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda Danfe gerados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraFsdaDanfeGerados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraPedidoDpec(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Pedidos de Dpec.");
		try {
			Query query = session.getNamedQuery("nfe.procuraPedidoDpec");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraZipNaoEnviados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Zip n�o enviados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraZipNaoEnviados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraZipDpecNaoEnviados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Dpec Zip n�o enviados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraZipDpecNaoEnviados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraZipFsdaNaoEnviados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda Zip n�o enviados.");
		try {
			Query query = session.getNamedQuery("nfe.procuraZipFsdaNaoEnviados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Nfe> procuraZipFsdaEnvioManual(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Fsda Zip envio manual.");
		try {
			Query query = session.getNamedQuery("nfe.procuraZipFsdaEnvioManual");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Nfe>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	public int solicitaEventosNotaFiscal(String chaveAcesso)
	{
		Nfe nfe = this.findByChaveAcesso(chaveAcesso);
		nfe.setConsultaEventos('1');
		try
		{
			NFeUpdateCommand updateCommand = new NFeUpdateCommand(nfe);
			updateCommand.execute();
		}
		catch(Exception e)
		{
			return 0;
		}
		return 1;
	}
}
