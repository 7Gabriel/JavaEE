package br.com.casadocodigo.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.models.SystemUser;

public class SecurityDao {

	@PersistenceContext(type=PersistenceContextType.EXTENDED) 
	private EntityManager manager;
	
	public SystemUser findByEmail(String email){
		return manager.createQuery("select su from SystemUser su where su.email = :email", SystemUser.class)
				.setParameter("email", email).getSingleResult();
	}
}
