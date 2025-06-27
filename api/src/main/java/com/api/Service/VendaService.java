package com.api.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.api.UsuarioRepository.*;
import com.api.modelos.Venda;

@Service
public class VendaService {

    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public Venda salvar(Venda venda) {
        if (venda.getData() == null) {
            throw new RuntimeException("A data da venda deve ser informada");
        }

        return repository.save(venda);
    }

    public List<Venda> get() {
        return repository.findAll();
    }

    public ResponseEntity<Venda> getById(Long id) {
        Optional<Venda> vendaOptional = repository.findById(id);
        return vendaOptional.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Venda removida com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover venda");
        }
    }

    public Venda alterarVenda(Venda vendaAlterada, Long id) {
        return repository.findById(id)
            .map(venda -> {
                venda.setData(vendaAlterada.getData());
                return repository.save(venda);
            })
            .orElse(null);
    }
}
