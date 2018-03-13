package br.com.casadocodigo.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Compra;

public class CompraDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Compra compra){
		manager.persist(compra);
	}

	public Compra buscarPorUUID(String uuid) {
		return manager.createQuery("select c from Compra c where c.uuid = :uuid", Compra.class)
		.setParameter("uuid", uuid).getSingleResult();
		
	}
}
