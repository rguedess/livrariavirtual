package br.com.universa.livraria.persistencia.connection;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.universa.livraria.entidade.Usuario;
import br.com.universa.livraria.persistencia.dao.IUsuarioDao;
import br.com.universa.livraria.persistencia.dao.UsuarioDao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author José Alexandre
 */
public class HibernateTest {

    public static void main(String[] args) throws SQLException {

        //@SuppressWarnings("unused")
//		Usuario usuario = new Usuario();
//        
//        usuario.setNome("Ricardo Guedes");
//        usuario.setUsuario("rguedess");
//        usuario.setSenha("12345678");
    	
    	IUsuarioDao udao = new UsuarioDao();
        for(int i=0;i<20;i++){
        	Usuario usuarioOutro = new Usuario();
        	
        	usuarioOutro.setNome("Rciardo" + i);
        	usuarioOutro.setUsuario("rguedess"+i);
        	usuarioOutro.setSenha("1"+i);

        	udao.gravaUsuario(usuarioOutro);
        }

        List<Usuario> users = new ArrayList<Usuario>();  
        
        users = udao.listaUsuario();
        
        for (Usuario usuario : users) {
			System.out.println(usuario.getNome());
		}
        
        //Session session = HibernateCon.getSessionFactory().openSession();
        //Transaction transacao = session.beginTransaction();
        
        
        //session.save(user);
        //t.commit();
        //System.out.println("ID do Pessoa: " + user.getId());
        
        //user = (Usuario) session.load(Usuario.class, 1L);
        //System.out.println(user.getNome());

        //session.close();

    }
}
