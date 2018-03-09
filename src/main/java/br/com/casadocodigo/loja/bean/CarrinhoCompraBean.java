package br.com.casadocodigo.loja.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.daos.LivroDao;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class CarrinhoCompraBean {

	@Inject
	private LivroDao dao;
	private CarrinhoCompras carrinho;
	
	public String add(Integer id){
		Livro livro = dao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);
		return "carrinho?faces-redirect=true";
	}
}
