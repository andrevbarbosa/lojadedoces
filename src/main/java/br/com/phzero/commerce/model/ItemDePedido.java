package br.com.phzero.commerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ItemDePedido {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	private Item item;
	
	private Integer quantidade;
	private Integer subTotal;
	
	@Transient
	private double subTotalCalculado;
	
	public double getSubTotalCalculado() {
		subTotalCalculado = subTotal==null?0:(double)subTotal/100;
		return subTotalCalculado;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
		this.getSubTotal();
	}
	public Integer getQuantidade() {
		return quantidade==null?0:quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		this.getSubTotal();
	}
	public Integer getSubTotal() {
		subTotal = item!=null && quantidade!=null? item.getPreco()*quantidade : 0; 
		subTotalCalculado = (double)subTotal/100;
		return subTotal;
	}
	public Integer getId() {
		return id;
	}
	
	
	
}
