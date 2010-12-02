package br.com.universa.livraria.persistencia.dao;

import java.util.List;

import br.com.universa.livraria.entidade.Livro;

public interface ILivroDao {

    public void gravaLivro(Livro livro);
    public List<Livro> listaLivros();
    public Livro buscaLivroPorId(long id);
    public List<Livro> buscaLivroPorTitulo(String titulo);
    public void excluiLivro(Livro livro);
    public void alteraLivro(Livro livro);
	
}
