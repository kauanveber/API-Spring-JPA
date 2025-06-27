package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Produto;
import com.api.modelos.Usuario;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
