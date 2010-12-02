package br.com.universa.livraria.persistencia.dao;


import java.util.List;

import javax.persistence.Query;


import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.universa.livraria.entidade.Livro;
import br.com.universa.livraria.persistencia.connection.HibernateCon;

public class LivroDao implements ILivroDao {

	
	public void gravaLivro(Livro livro) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
        session.save(livro);
        transacao.commit();
		
	}

	
	public Livro buscaLivroPorId(long id) {
		
		return null;
	}

	
	public List<Livro> buscaLivroPorTitulo(String titulo) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        
        org.hibernate.Query query = session.createQuery("from Livro where titulo like :titulo");
		query.setParameter("titulo", "%" + titulo + "%");
		
		@SuppressWarnings("rawtypes")
        List lista = query.list(); 
		
        return lista;
	}

	
	public List<Livro> listaLivros() {

		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        @SuppressWarnings("rawtypes")
		List lista = session.createQuery("from Livro").list();
        t.commit();
        return lista;
	}

	
	public void excluiLivro(Livro livro) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
        session.delete(livro);
        transacao.commit();
		
	}

	
	public void alteraLivro(Livro livro) {
		
		Session session = HibernateCon.getSessionFactory().openSession();
        Transaction transacao = session.beginTransaction();
        session.update(livro);
        transacao.commit();
		
	}

}
