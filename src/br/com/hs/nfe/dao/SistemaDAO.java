package br.com.hs.nfe.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Sistema;

public class SistemaDAO extends GenericDAO<Sistema> {
	public Sistema getSistema() {
		Session session = getSession();
		session.beginTransaction();
		try {
			Query query = session.getNamedQuery("sistema.getSistema");
			return (Sistema) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
		
	}
}
