package br.com.casadocodigo.loja.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.casadocodigo.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class AdminListaLivrosBean {

	@Inject
	private LivroDao dao;
	private List<Livro> livros = new ArrayList<>();
	@Inject
	private FacesContext context;

	public List<Livro> getLivros() {
		this.livros = dao.listar();
		return livros;
	}
	
	
}
