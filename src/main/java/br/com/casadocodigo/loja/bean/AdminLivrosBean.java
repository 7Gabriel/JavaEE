package br.com.casadocodigo.loja.bean;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.daos.AutorDao;
import br.com.casadocodigo.daos.LivroDao;
import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	@Inject
	private LivroDao dao;
	private Livro livro = new Livro();
	
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext currentInstance;
	
	private Part capaLivro;
	
	public AdminLivrosBean() {
		this.currentInstance = FacesContext.getCurrentInstance();
	}
	
	@Transactional
	public String salvar() throws IOException{
		FileSaver fileSaver = new FileSaver();
		
		verificaExtensão(fileSaver);
		dao.salvar(livro);
		currentInstance.getExternalContext().getFlash().setKeepMessages(true);
		currentInstance.addMessage(null, new FacesMessage("Livro salvo com sucesso"));
		return "lista?faces-redirect=true";
	}

	private void verificaExtensão(FileSaver fileSaver) {
		if(capaLivro.getSubmittedFileName().contains(".png")){
			livro.setCapaPath(fileSaver.write(capaLivro, "png"));
		}else if(capaLivro.getSubmittedFileName().contains(".jpg")){
			livro.setCapaPath(fileSaver.write(capaLivro, "jpg"));
		}else if(capaLivro.getSubmittedFileName().contains(".xml")){
			livro.setCapaPath(fileSaver.write(capaLivro, "xml"));
		}else if(capaLivro.getSubmittedFileName().contains(".txt")){
			livro.setCapaPath(fileSaver.write(capaLivro, "txt"));
		}
	}

	
	
	public List<Autor> getAutores(){
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}
	

}
