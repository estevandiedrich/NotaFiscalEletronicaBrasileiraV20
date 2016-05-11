package br.com.hs.nfe.hb;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class Command {
	
	private static final Logger logger = Logger.getLogger("Command");
	protected Session	session;
	private int			count	= 0;
	
	public void execute() {
		boolean conseguiu = false;
		do {
			try {
				session = Persistencia.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				doJob();
				session.getTransaction().commit();
				conseguiu = true;
			} catch (HibernateException e) { // JDBCException
				logger.error("Quando o erro for de Unique Constraint Violeted para a tabela InutilizaNotaFiscal, é porque o sistema está tentando inserir um registro para uma nota já Inutilizada! Este erro deve ser desconsiderado!");
				logger.error(e.getMessage(), e);
				// tratarExcessao(e);
				try {
					logger.error(e.getMessage(), e);
					session.getTransaction().rollback();
				} catch (Throwable e2) {
					logger.debug(e2.getMessage(), e2);
				}
				count++;
				logger.debug("ERRO AO TENTAR EXECUTAR COMANDO, TENTATIVA [" + count + "]:" + e.getMessage(), e);
				if (count == 3) {
					logger.error("ERRO AO TENTAR EXECUTAR COMANDO, TENTATIVA [" + count + "]:" + e.getMessage(), e);
					throw e;
				}
				try {
					Thread.sleep(700);
				} catch (Exception ex) {
				}
			} catch (Throwable t) {
				logger.error(t.getMessage(), t);
			}
		} while (!conseguiu);
	}
	
	protected Object save(Object o) {
		return session.save(o);
	}
	
	protected Object save(String s, Object o) {
		return session.save(s, o);
	}
	
	protected void delete(Object o) {
		session.delete(o);
	}
	
	protected void delete(String s, Object o) {
		session.delete(s, o);
	}
	
	protected Object merge(Object o) {
		return session.merge(o);
	}
	
	protected Object merge(String s, Object o) {
		return session.merge(s, o);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T findByCod(Class<T> c, int cod) {
		return (T) session.get(c, new Integer(cod));
	}
	
	public final List<?> findAll(Class<?> c) {
		return session.createCriteria(c).list();
	}
	
	public final Query getNamedQuery(String name) {
		return session.getNamedQuery(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T findByCod(Class<T> classe, Serializable obj) {
		return (T) session.get(classe, obj);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> classe, Serializable obj, LockMode lock) {
		return (T) session.get(classe, obj, lock);
	}
	
	protected abstract void doJob();
	
}
