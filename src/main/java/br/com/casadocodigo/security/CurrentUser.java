package br.com.casadocodigo.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.daos.SecurityDao;
import br.com.casadocodigo.loja.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;
	
	@Inject
	private SecurityDao dao;
	
	private SystemUser systemUser;
	
	public SystemUser get(){
		return systemUser;
	}
	
	@PostConstruct
	public void loadSystemUser(){
		Principal principal = request.getUserPrincipal();
		if(principal != null){
			String email = request.getUserPrincipal().getName();
			systemUser = dao.findByEmail(email);
		}
	}
	
	public boolean hasRole(String role){
		return request.isUserInRole(role);
	}
	
	public String logout(){
		request.getSession().invalidate();
		return "/livros/lista.xhtml?faces-redirect=true";
	}
}
