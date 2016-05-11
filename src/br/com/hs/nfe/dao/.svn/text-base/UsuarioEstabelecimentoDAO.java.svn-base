package br.com.hs.nfe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Estabelecimento;
import br.com.hs.nfe.entity.UsuarioEstabelecimento;

public class UsuarioEstabelecimentoDAO extends GenericDAO<UsuarioEstabelecimento>{
	private static final Logger logger = Logger.getLogger("UsuarioEstabelecimentoDAO");
	@SuppressWarnings("unchecked")
	public List<Estabelecimento> findEstabelecimentoByUsuario(int codUsuario) {
		Session session = getSession();
		session.beginTransaction();
		
		try {
			Query query = session.getNamedQuery("usuarioEstabelecimento.findEstabelecimentoByUsuario");
			query.setInteger("_codUsuario", codUsuario);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<UsuarioEstabelecimento> findByUsuario(int codUsuario) {
		Session session = getSession();
		session.beginTransaction();
		
		try {
			Query query = session.getNamedQuery("usuarioEstabelecimento.findByUsuario");
			query.setInteger("_codUsuario", codUsuario);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
}
