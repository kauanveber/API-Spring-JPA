package com.api.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.Service.VendaService;
import com.api.modelos.Venda;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Venda venda) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(venda));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Venda> listarTodas() {
        return service.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.deletar(id);
    }

    @PutMapping("/{id}")
    public Venda update(@PathVariable Long id, @RequestBody Venda vendaAtualizada) {
        return service.alterarVenda(vendaAtualizada, id);
    }
}
