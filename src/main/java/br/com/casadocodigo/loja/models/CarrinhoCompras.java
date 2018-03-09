package br.com.casadocodigo.loja.models;

import java.util.HashSet;
import java.util.Set;

public class CarrinhoCompras {

	private Set<CarrinhoItem> itens = new HashSet<CarrinhoItem>();
	 
	public void add(CarrinhoItem item){
		itens.add(item);
	}
//	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
//	
//	public void add(CarrinhoItem item){
//		itens.put(item, quantidade);
//	}
}
