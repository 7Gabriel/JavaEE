package br.com.casadocodigo.loja.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
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
	private List<Integer> autoresId = new ArrayList<Integer>();
	@Inject
	private AutorDao autorDao;
	
	@Transactional
	public String salvar(){
		autoresId.forEach(id -> livro.getAutores().add(new Autor(id)));
		dao.salvar(livro);
		System.out.println("Livro cadastrado : "+ livro);
		
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

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}
	
	

}