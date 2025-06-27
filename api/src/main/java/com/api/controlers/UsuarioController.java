package com.api.controlers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.UsuarioService;
import com.api.UsuarioRepository.UsuarioRepository;
import com.api.modelos.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService service;

	public UsuarioController( UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<?>post (@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(usuario));
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public List<Usuario> listarTodos() {
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
	public Usuario update(@PathVariable("id") Long id, @RequestBody Usuario usuarioAtualizado) {
	  return service.alterarUsuario(usuarioAtualizado, id);
	}
	
}
