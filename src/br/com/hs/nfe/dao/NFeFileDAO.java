package br.com.hs.nfe.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.NFeFile;

public class NFeFileDAO extends GenericDAO<NFeFile> {
	private static final Logger logger = Logger.getLogger("NFeFileDAO");
	
	public NFeFile findByChaveAcesso(String chaveAcesso) {
		Session session = getSession();
		session.beginTransaction();
		logger.info("Procurando Nota Fiscal com chave de acesso: [" + chaveAcesso + "]");
		
		try {
			Query query = session.getNamedQuery("nfefile.findByChaveAcesso");
			query.setString("_chaveAcesso", chaveAcesso);
			return (NFeFile) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
	}
}
