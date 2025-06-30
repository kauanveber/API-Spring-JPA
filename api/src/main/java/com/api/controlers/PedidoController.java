package com.api.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.CategoriaService;
import com.api.Service.PedidoService;
import com.api.modelos.Categoria;
import com.api.modelos.Pedido;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	private PedidoService service;

	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<?>post (@RequestBody Pedido pedido) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(pedido));
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public List<Pedido> listarTodos() {
		return service.get();
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		return service.getById(id);
	}

	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
	   return service.deletar(id);
	}
	
	@PutMapping("/{id}")
	public Pedido update(@PathVariable("id") Long id, @RequestBody Pedido pedidoAtualizado) {
	  return service.alterarPedido(pedidoAtualizado, id);
	}
}
