package br.com.phzero.commerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	private Cliente cliente;
	
	@OneToMany
	private List<ItemDePedido> itens;
	
	private Integer total;
	private String status;
	
	
	public void addItemDePedido(ItemDePedido itemDePedido) {
		getItens().add(itemDePedido);
		getTotal();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemDePedido> getItens() {
		if (itens == null) itens = new ArrayList<ItemDePedido>();
		return itens;
	}
	
	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
		getTotal();
	}
	
	public Integer getTotal() {
		if (itens==null || itens.size()==0) {
			total = 0;
		}
		else {
			total = itens.stream().mapToInt(ItemDePedido::getSubTotal).sum();
		}
		
		return total;
	}
	
	public void atualizarTotal() {
		getTotal();
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	

}
