package br.com.hs.nfe.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.hb.Persistencia;

public class GenericDAO<E> {
	private static final Logger logger = Logger.getLogger("GenericDAO");
	public Session getSession() {
		logger.info("getSession");
		return Persistencia.getSessionFactory().getCurrentSession();
	}
	
	protected void transferParams(Query query, Vector<Object> params) {
		int count = 0;
		for (Object o : params) {
			if (o instanceof Integer) {
				query.setInteger(count, (Integer) o);
				
			} else if (o instanceof String) {
				query.setString(count, (String) o);
				
			} else if (o instanceof Date) {
				query.setDate(count, (Date) o);
				
			} else {
				throw new IllegalArgumentException("Tipo de parâmetro não identificado: " + o.getClass());
			}
			count++;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Class<E> getTabela() {
		logger.info("getTabela");
		return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@SuppressWarnings("unchecked")
	public final List<E> findAll() {
		Session s = null;
		try {
			s = getSession();
			s.beginTransaction();
			return s.createCriteria(getTabela()).list();
		} finally {
			s.getTransaction().commit();
		}
		
	}
}
