package br.com.casadocodigo.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = -5583211175969745732L;

	@PersistenceContext
	private EntityManager manager;

	
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
}
