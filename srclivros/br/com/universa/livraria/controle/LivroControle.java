package br.com.universa.livraria.controle;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.universa.livraria.entidade.Livro;
import br.com.universa.livraria.persistencia.dao.ILivroDao;
import br.com.universa.livraria.persistencia.dao.LivroDao;

@ManagedBean(name = "livroControle")
@SessionScoped
public class LivroControle {

	Livro livro = new Livro();
	int botao = 1;
	boolean listaLivro = true;

	private Logger logger = Logger.getLogger(LivroControle.class.getName());

	// boolean consultaLivro;

	/*
	 * public boolean isConsultaLivro() { return consultaLivro; }
	 * 
	 * public void setConsultaLivro(boolean consultaLivro) { this.consultaLivro
	 * = consultaLivro; }
	 */

	public boolean isListaLivro() {
		return listaLivro;
	}

	public void setListaLivro(boolean listaLivro) {
		this.listaLivro = listaLivro;
	}

	public void setTipoConsulta() {
		this.listaLivro = false;
	}

	@SuppressWarnings("rawtypes")
	private DataModel listaLivroDataModel;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getConsultarLivros() {
		// consultaLivro = true;
		ILivroDao livroDao = new LivroDao();
		List<Livro> lista = livroDao.buscaLivroPorTitulo(livro.getTitulo());
		listaLivroDataModel = new ListDataModel(lista);
		return listaLivroDataModel;
	}

	public String cadastrarLivro() {

		ILivroDao livroDao = new LivroDao();
		livroDao.gravaLivro(livro);

		return "listaLivro";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListarLivros() {
		// consultaLivro = false;
		ILivroDao livroDao = new LivroDao();
		List<Livro> lista = livroDao.listaLivros();
		listaLivroDataModel = new ListDataModel(lista);
		return listaLivroDataModel;
	}

	public String preparaAlteracaoLivro() {
		setBotao(2);
		livro = (Livro) (listaLivroDataModel.getRowData());
		return "cadastroLivro";
	}

	public String alterarLivro() {

		ILivroDao livroDao = new LivroDao();
		livroDao.alteraLivro(livro);
		setBotao(1);
		return "listaLivro";

	}

	public String excluirLivro() {

		Livro livroParaExcluir = (Livro) (listaLivroDataModel.getRowData());
		ILivroDao livroDao = new LivroDao();
		livroDao.excluiLivro(livroParaExcluir);

		return "listaLivro";
	}

	public String detalharLivro() {
		livro = (Livro) (listaLivroDataModel.getRowData());
		return "detalhaLivro";

	}

	public String zerarVariaveis() {
		listaLivro = true;
		setBotao(1);
		livro = null;
		livro = new Livro();
		return "listaLivro";
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public int getBotao() {
		return botao;
	}

	public void setBotao(int botao) {
		this.botao = botao;
	}

	public void handleFileUpload(FileUploadEvent event) {

		UploadedFile uploadedFile = event.getFile();
		livro.setImagem(uploadedFile.getContents());
		String contentType = uploadedFile.getContentType();

		logger.info("Uploaded: " + uploadedFile.getFileName());

		// FacesMessage msg = new FacesMessage("Sucesso:",
		// uploadedFile.getFileName() + " upload realizado.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
