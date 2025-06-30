package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.modelos.Produto;
import com.api.modelos.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Produto p WHERE p.categoria.id = :categoriaId")
	void deleteByCategoriaId(@Param("categoriaId") Long categoriaId);

}
