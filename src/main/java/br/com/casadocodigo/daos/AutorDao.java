package br.com.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Autor;

public class AutorDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Autor> listar(){
		return em.createQuery("select a from Autor a", Autor.class).getResultList();
	}
}
