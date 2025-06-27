package com.api.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.UsuarioRepository.UsuarioRepository;

import com.api.modelos.Usuario;

@Service
public class UsuarioService {
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Usuario salvar(Usuario usuario) {
		if (usuario.getNome().isEmpty()) {
			throw new RuntimeException("Nome inválido");
		}

		if (usuario.getEmail().isEmpty()) {
			throw new RuntimeException("O email deve ser informado");
		}

		if (!usuario.getEmail().contains("@")) {
			throw new RuntimeException("Emáil inválido");
		}

		return repository.save(usuario);
	}

	public List<Usuario> get() {
		return repository.findAll();
	}

	public ResponseEntity<Usuario> getById(Long id) {
		return repository.findById(id).map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok("Usuário removido");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover usuário");
		}

	}
	
	public Usuario alterarUsuario(Usuario usuarioAlterado, Long id) {
	    return repository.findById(id)
	            .map(usuario -> {
	                usuario.setNome(usuarioAlterado.getNome());
	                usuario.setEmail(usuarioAlterado.getEmail());
	                return repository.save(usuario);
	            })
	            .orElse(null);
	}
}
