package com.api.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.ProdutoService;
import com.api.Service.VendaService;
import com.api.modelos.Produto;
import com.api.modelos.Venda;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	 private final ProdutoService service;

	    public ProdutoController(ProdutoService service) {
	        this.service = service;
	    }

	    @PostMapping
	    public ResponseEntity<?> post(@RequestBody Produto produto) {
	        try {
	            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }

	    @GetMapping
	    public List<Produto> listarTodas() {
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
	    public Produto update(@PathVariable Long id, @RequestBody Produto produtoAtualizada) {
	        return service.alteraProduto(produtoAtualizada, id);
	    }
}
