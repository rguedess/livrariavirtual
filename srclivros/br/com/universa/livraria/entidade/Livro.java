package br.com.universa.livraria.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Livro implements Serializable {

	@Id
	@GeneratedValue
	private String id;
	private String titulo;
	private String autor;
	private String paginas;
	private String editora;
	private String isbn;
	private byte[] imagem;

	public Livro() {

	}

	public Livro(String id, String titulo, String autor, String pagina,
			String editora, String isbn) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = pagina;
		this.editora = editora;
		this.isbn = isbn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public byte[] getImagem() {
		return imagem;
	}

}
