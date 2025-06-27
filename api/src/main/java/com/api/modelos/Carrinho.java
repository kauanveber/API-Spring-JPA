package com.api.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Carrinho {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Venda venda;

	@ManyToOne
	private Produto produto;
	private double precoItem;
	private int quantidade;

	public Carrinho(Long id, Venda venda, Produto produto, double precoItem, int quantidade) {

		this.id = id;
		this.venda = venda;
		this.produto = produto;
		this.precoItem = precoItem;
		this.quantidade = quantidade;
	}

	public Carrinho(Venda venda, Produto produto, double precoItem, int quantidade) {

		this.venda = venda;
		this.produto = produto;
		this.precoItem = precoItem;
		this.quantidade = quantidade;
	}
	
	

	public Carrinho() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getPrecoItem() {
		return precoItem;
	}

	public void setPrecoItem(double precoItem) {
		this.precoItem = precoItem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
