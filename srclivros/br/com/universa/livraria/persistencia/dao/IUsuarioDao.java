package br.com.universa.livraria.persistencia.dao;

import java.util.List;

import br.com.universa.livraria.entidade.Usuario;

public interface IUsuarioDao {

    public void gravaUsuario(Usuario usuario);
    public Usuario buscaUsuarioPorId(long id);
    public Usuario buscaUsuarioPorUsuario(String usuario);
    public List<Usuario> listaUsuario();
    public void excluiUsuario(Usuario usuario);
    public void alteraUsuario(Usuario usuario);
	
}
