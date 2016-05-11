package br.com.hs.nfe.hb;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Persistencia {
	private static final Logger logger = Logger.getLogger("Persistencia");
	private static SessionFactory sessionFactory = null;
	static {
		String hibernateFile = System.getProperty("nfe.hibernate.conf", "hibernate.cfg.xml");
		logger.info("Hibernate File: [" + hibernateFile + "]");
		Configuration c = new Configuration();
		URL url = Thread.currentThread().getContextClassLoader().getResource(hibernateFile);
		File file = new File(hibernateFile);
		if(url != null)
		{
			c.configure(url);
		}
		else
		{
			c.configure(file);
		}
		sessionFactory = c.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	public Object findByUID(Class<?> _class, int _uid) {
		return (Object) sessionFactory.getCurrentSession().get(_class, new Integer(_uid));
	}
	
	public List<?> findAll(Class<?> _class) {
		return sessionFactory.getCurrentSession().createCriteria(_class).list();
	}
	
	protected Query getNamedQuery(String queryName) {
		return sessionFactory.getCurrentSession().getNamedQuery(queryName);
	}
	
	protected Query getCreatedQuery(String query) {
		return sessionFactory.getCurrentSession().createQuery(query);
	}
	
	protected boolean hasValue(String value) {
		return value != null && value.length() > 0;
	}
}
