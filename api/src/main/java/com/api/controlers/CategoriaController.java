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

import com.api.Service.CategoriaService;
import com.api.Service.UsuarioService;
import com.api.modelos.Categoria;
import com.api.modelos.Usuario;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private CategoriaService service;

	public CategoriaController(CategoriaService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<?>post (@RequestBody Categoria categoria) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(categoria));
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public List<Categoria> listarTodos() {
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
	public Categoria update(@PathVariable("id") Long id, @RequestBody Categoria categoriaAtualizado) {
	  return service.alterarCategoria(categoriaAtualizado, id);
	}
}
