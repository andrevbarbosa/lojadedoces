package br.com.phzero.commerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Pedido {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	private Cliente cliente;
	
	@ManyToMany
	private List<ItemDePedido> itens;
	
	private String cupomDesconto;
	private Integer total;
	private Integer desconto;
	private Integer acrescimo;
	private String moipId;
	private String status;
	
	@Transient
	private Integer totalLiquido;
	
	@Transient
	private double descontoCalculado;
	
	@Transient
	private double acrescimoCalculado;
	
	@Transient
	private double totalCalculado;
	
	public void calcularTotais() {
		this.descontoCalculado = desconto==null?0:(double)desconto/100;
		this.acrescimoCalculado = acrescimo==null?0:(double)acrescimo/100;
		this.totalCalculado = totalLiquido==null?0:(double)totalLiquido/100;
	}
	
	public double getDescontoCalculado() {
		return descontoCalculado;
	}
	
	public double getAcrescimoCalculado() {
		return acrescimoCalculado;
	}
	
	public double getTotalCalculado() {
		return totalCalculado;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setMoipId(String moipId) {
		this.moipId = moipId;
	}
	
	public String getMoipId() {
		return moipId;
	}
	
	public String getCupomDesconto() {
		return cupomDesconto;
	}
	
	public void setCupomDesconto(String cupomDesconto) {
		if (cupomDesconto==null || cupomDesconto.trim().length()==0) return;
		
		this.cupomDesconto = cupomDesconto;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemDePedido> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}
	
	public void setAcrescimo(Integer acrescimo) {
		this.acrescimo = acrescimo;
		getTotalLiquido();
	}
	
	public Integer getAcrescimo() {
		return this.acrescimo==null?0:this.acrescimo;
	}
	
	public Integer getTotal() {
		total = itens.stream().mapToInt(ItemDePedido::getSubTotal).sum();

		return total;
	}
	
	public Integer getTotalLiquido() {
		totalLiquido = getTotal() - getDesconto() + getAcrescimo();
		
		return totalLiquido;
	}
	
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
		getTotalLiquido();
	}

	
	public Integer getDesconto() {
		return this.desconto==null?0:this.desconto;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setCart(Cart cart) {
		if (itens==null) itens = new ArrayList<ItemDePedido>();
		
		getItens().addAll(cart.getItens());
		setCliente(cart.getCliente());
		getTotal();
		getDesconto();
		getTotalLiquido();
		calcularTotais();
	}
	
	
}
