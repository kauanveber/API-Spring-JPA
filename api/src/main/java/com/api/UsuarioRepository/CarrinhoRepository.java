package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Carrinho;
import com.api.modelos.Usuario;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}
