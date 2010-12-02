package br.com.universa.livraria.controle;

import java.io.IOException;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.universa.livraria.entidade.Usuario;
import br.com.universa.livraria.persistencia.dao.IUsuarioDao;
import br.com.universa.livraria.persistencia.dao.UsuarioDao;

@ManagedBean
@SessionScoped
public class UsuarioControle {

	private Usuario usuario = new Usuario();
	private boolean cadastroContaRealizado;
	private String mensagemCadastroConta;
	private boolean logado;

	public String logar()throws Exception{

        IUsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuarioCadastrado = new Usuario(); 
        
        usuarioCadastrado = usuarioDao.buscaUsuarioPorUsuario(usuario.getUsuario());
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(usuarioCadastrado != null){
            if(usuario.getUsuario().equals(usuarioCadastrado.getUsuario()) && usuario.getSenha().equals(usuarioCadastrado.getSenha())){
            	usuario.setId(usuarioCadastrado.getId());
            	usuario.setNome(usuarioCadastrado.getNome());
            	logado = true;
            	session.setAttribute("user", logado);
            	rp.sendRedirect(rq.getContextPath() + "/livraria/index.jsf");
            	return "success";
            }else{
            	logado = false;
                session.setAttribute("user", null);
                session.removeAttribute("user");
            	rp.sendRedirect(rq.getContextPath() + "/livraria/login.jsf");
            	return "failure";
            }
        }
        return "failure";
	}
	
	public String deslogar() {
		
		HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse rp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.invalidate();
		try {
			rp.sendRedirect(rq.getContextPath() + "/livraria/login.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "deslogadoOk";
	}

	/**
	 * TODO Não deixar gravar usuário já existente no método abaixo...
	 * @return
	 */
	
	public String criarNovaConta(){
		
        IUsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.gravaUsuario(usuario);
        
        Usuario usuarioCadastrado = new Usuario();
        usuarioCadastrado = usuarioDao.buscaUsuarioPorId(usuario.getId());
        
        if(usuario.getId() == usuarioCadastrado.getId()){
        	setCadastroContaRealizado(true);
        	setMensagemCadastroConta(usuario.getNome() + ", sua conta foi criada com sucesso !");
        }else{
        	setCadastroContaRealizado(false);
        }

        return "novaConta";
		
	}
	
	public void zerarVariaveisLogin(){
		cadastroContaRealizado = false;
		mensagemCadastroConta = null;
		usuario = null;
		usuario = new Usuario();
	}
	
	public boolean isCadastroContaRealizado() {
		return cadastroContaRealizado;
	}

	public void setCadastroContaRealizado(boolean cadastroConta) {
		this.cadastroContaRealizado = cadastroConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getMensagemCadastroConta() {
		return mensagemCadastroConta;
	}
	
	public void setMensagemCadastroConta(String mensagemCadastroConta) {
		this.mensagemCadastroConta = mensagemCadastroConta;
	}
	
	public boolean isLogado() {
		return logado;
	}
	
	public void setLogado(boolean logado) {
		this.logado = logado;
	}

}
