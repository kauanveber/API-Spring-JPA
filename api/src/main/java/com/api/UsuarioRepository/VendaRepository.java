package com.api.UsuarioRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.modelos.Usuario;
import com.api.modelos.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
