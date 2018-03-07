package br.com.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Livro livro){
		manager.persist(livro);
	}
	
	public List<Livro> listar(){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select distinct(l) from Livro l ");
		jpql.append("join fetch l.autores");
		return manager.createQuery(jpql.toString(), Livro.class).getResultList();
	}
}
