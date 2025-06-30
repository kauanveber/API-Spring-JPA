package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.modelos.Carrinho;
import com.api.modelos.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Carrinho c WHERE c.produto.id IN (SELECT p.id FROM Produto p WHERE p.categoria.id = :categoriaId)")
	void deleteByProdutoCategoriaId(@Param("categoriaId") Long categoriaId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Carrinho c WHERE c.produto.id = :produtoId")
	void deleteByProdutoId(@Param("produtoId") Long produtoId);
	
}
