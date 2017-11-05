package br.com.phzero.commerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pagamento {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	private Pedido pedido;
	
	private Integer numeroParcelas;
	private String formaPagamento;
	
	@Column(length=500)
	private String hashCartao;
	
	private String moipId;
	private String status;
	
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
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}
	
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	public String getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public String getHashCartao() {
		return hashCartao;
	}
	
	public void setHashCartao(String hashCartao) {
		this.hashCartao = hashCartao;
	}
	
	public Integer getId() {
		return id;
	}
	
	
}
