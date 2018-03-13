package br.com.casadocodigo.loja.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemRole implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String nome;

	public SystemRole() {

	}

	public SystemRole(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	
}
