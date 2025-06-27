package com.api.modelos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;

	public Pedido() {
		
	}

	public Pedido(LocalDate data, List<Produto> produtos) {
		this.data = data;
		this.produtos = produtos;
	}

	public Pedido(Long id, LocalDate data, List<Produto> produtos) {

		this.id = id;
		this.data = data;
		this.produtos = produtos;
	}

	@ManyToMany
	@JoinTable(
	    name = "tb_pedidos_produtos",
	    joinColumns = @JoinColumn(name = "pedido_id"),
	    inverseJoinColumns = @JoinColumn(name = "produto_id"),
	    uniqueConstraints = @UniqueConstraint(columnNames = {"pedido_id", "produto_id"})
	)
	private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
