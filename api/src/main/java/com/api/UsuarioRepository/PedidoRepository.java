package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Pedido;
import com.api.modelos.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
