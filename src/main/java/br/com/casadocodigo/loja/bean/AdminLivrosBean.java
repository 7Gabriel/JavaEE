package br.com.casadocodigo.loja.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.daos.AutorDao;
import br.com.casadocodigo.daos.LivroDao;
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
	
	public AdminLivrosBean() {
		this.currentInstance = FacesContext.getCurrentInstance();
	}
	
	@Transactional
	public String salvar(){
		dao.salvar(livro);
		currentInstance.getExternalContext().getFlash().setKeepMessages(true);
		currentInstance.addMessage(null, new FacesMessage("Livro salvo com sucesso"));
		return "lista?faces-redirect=true";
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
	

}
