package br.com.hs.nfe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Evento;

public class EventoDAO extends GenericDAO<Evento>{
	private static final Logger logger = Logger.getLogger("EventoDAO");
	@SuppressWarnings("unchecked")
	public List<Evento> procuraEventosGerados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Eventos Gerados.");
		try {
			Query query = session.getNamedQuery("evento.procuraEventosGerados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Evento> procuraEventosAssinados(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Eventos Gerados.");
		try {
			Query query = session.getNamedQuery("evento.procuraEventosAssinados");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Evento> procuraEventosValidos(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Eventos Validos.");
		try {
			Query query = session.getNamedQuery("evento.procuraEventosValidos");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Evento> getEventosNotaFiscal(String chaveAcesso)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Eventos Validos.");
		try {
			Query query = session.getNamedQuery("evento.getEventosNotaFiscal");
			query.setString("_chaveAcesso", chaveAcesso);
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Evento> procuraTxtRetornoPendente(String cnpj,String pe)
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Txt retorno pendente.");
		try {
			Query query = session.getNamedQuery("evento.procuraTxtRetornoPendente");
			query.setString("_cnpj", cnpj);
			query.setString("_pe", pe);
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Evento> procuraTxtEntradaRetornoPendente()
	{
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Txt retorno pendente.");
		try {
			Query query = session.getNamedQuery("evento.procuraTxtEntradaRetornoPendente");
			return (List<Evento>)query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
}
