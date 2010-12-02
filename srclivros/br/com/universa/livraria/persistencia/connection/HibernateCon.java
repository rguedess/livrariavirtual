/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.universa.livraria.persistencia.connection;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

import br.com.universa.livraria.entidade.Livro;
import br.com.universa.livraria.entidade.Usuario;

public class HibernateCon {

    private static SessionFactory sessionFactory;

    private HibernateCon() {
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Usuario.class);
                ac.addAnnotatedClass(Livro.class);
                sessionFactory = ac.configure().buildSessionFactory();
                //SchemaExport se = new SchemaExport(ac);
                //se.create(true, true);

            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

            return sessionFactory;

        } else {
            return sessionFactory;
        }
        
    }

    //public static void main(String[] args) {
    //    HibernateUtil.getSessionFactory();
    //}

}
