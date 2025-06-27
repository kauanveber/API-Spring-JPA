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

import com.api.Service.CarrinhoService;
import com.api.Service.CategoriaService;
import com.api.modelos.Carrinho;
import com.api.modelos.Categoria;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
	private CarrinhoService service;

	public CarrinhoController(CarrinhoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody Carrinho carrinho) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(carrinho));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@GetMapping
	public List<Carrinho> listarTodos() {
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
	public Carrinho update(@PathVariable("id") Long id, @RequestBody Carrinho carrinhoAtualizado) {
		return service.alterarCarrinho(carrinhoAtualizado, id);
	}
}
