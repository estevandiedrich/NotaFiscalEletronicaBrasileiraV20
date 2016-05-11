package br.com.hs.nfe.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.hs.nfe.entity.Usuario;
import br.com.hs.nfe.util.Base64Utils;


public class UsuarioDAO extends GenericDAO<Usuario> 
{
	
	public static void clearCache() {
	}
	
	/**
	 * Tem por objetivo validar se o usuário existe no banco.
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 */
	public boolean validateUsuario(String usuario, String password) {
		String pw = Base64Utils.encode(password);
		Session session = getSession();
		session.beginTransaction();
		Usuario u = null;
		try {
			Query query = session.getNamedQuery("usuario.findByUsrSenha");
			query.setString("_usuario", usuario);
			query.setString("_senha", pw);
			u = (Usuario) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
		return (u != null);
	}
	
	public Usuario findByUsuario(String usuario) {
		Session session = getSession();
		session.beginTransaction();
		try {
			Query query = session.getNamedQuery("usuario.findByUsr");
			query.setString("_usuario", usuario);
			return (Usuario) query.uniqueResult();
		} finally {
			session.getTransaction().commit();
		}
		
	}

	
	public Usuario findByCod(int cod) {
		Usuario usuario = null;
		if (usuario == null) {
			Session session = getSession();
			try {
				session.beginTransaction();
				usuario = (Usuario) session.get(getTabela(), new Integer(cod));
			} finally {
				session.getTransaction().commit();
			}
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findUserAndAdmByAdm(int cod) {
		Session session = getSession();
		session.beginTransaction();
		try {
			Query query = session.getNamedQuery("usuario.findUserAndADMByAdm");
			query.setInteger("_codUsuario", cod);
			query.setInteger("_tpUsuario", Usuario.TIPO_USUARIO_USUARIO);
			query.setInteger("_tuAdmin", Usuario.TIPO_USUARIO_ADMINISTRADOR);
			return query.list();
		} finally {
			session.getTransaction().commit();
		}
	}
}
