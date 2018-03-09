package br.com.casadocodigo.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.models.Livro;

@Stateful
public class LivroDao {

	@PersistenceContext(type=PersistenceContextType.EXTENDED) 
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

	public List<Livro> ultimaLancamentos() {
		String jpql = "select l from Livro l order by l.dataPublicacao DESC";
		return manager.createQuery(jpql, Livro.class).setMaxResults(5).getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.dataPublicacao DESC";
		return manager.createQuery(jpql, Livro.class).setFirstResult(6).getResultList();
	}

	public Livro buscarPorId(Integer id) {
		//return manager.find(Livro.class, id);
		String jpql = "select l from Livro l join fetch l.autores where l.id = :id";
		return manager.createQuery(jpql, Livro.class).setParameter("id", id).getSingleResult();
	}
}
