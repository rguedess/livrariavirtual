package br.com.universa.livraria.persistencia.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.universa.livraria.entidade.Usuario;
import br.com.universa.livraria.persistencia.connection.HibernateCon;

public class UsuarioDao implements IUsuarioDao {

	
	public void gravaUsuario(Usuario usuario) {

		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
        session.save(usuario);
        transacao.commit();

	}
	
	public Usuario buscaUsuarioPorId(long id) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
		return (Usuario) session.load(Usuario.class, id);
	}

	public Usuario buscaUsuarioPorUsuario(String usuario) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
		Query query = session.createQuery(" from Usuario usuario where usuario.usuario = :usuario");
		query.setParameter("usuario", usuario);
		return (Usuario) query.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listaUsuario() {

		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
        @SuppressWarnings("rawtypes")
		List lista = session.createQuery("from Usuario").list();
        transacao.commit();

        return lista;

	}

	
	public void excluiUsuario(Usuario usuario) {

		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
		session.delete(usuario);
		transacao.commit();

	}

	
	public void alteraUsuario(Usuario usuario) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
		session.update(usuario);
		transacao.commit();

	}

}
