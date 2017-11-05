package br.com.phzero.commerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	private String nome;
	private Integer preco;
	private String categoria;
	private String detalhes;
	
	@Transient
	private double precoCalculado;
	
	public double getPrecoCalculado() {
		precoCalculado = preco==null?0:(double)preco/100;
		return precoCalculado;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public Integer getId() {
		return id;
	}
	
	
}
