package com.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.UsuarioRepository.CarrinhoRepository;
import com.api.UsuarioRepository.CategoriaRepository;
import com.api.UsuarioRepository.ProdutoRepository;
import com.api.UsuarioRepository.UsuarioRepository;
import com.api.modelos.Categoria;
import com.api.modelos.Usuario;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	CarrinhoRepository carrinhoRepository;

	

	public Categoria salvar(Categoria categoria) {
		if (categoria.getDescricao().isEmpty()) {
			throw new RuntimeException("Descrição inválido");
		}

		return repository.save(categoria);
	}
	public List<Categoria> get() {
		return repository.findAll();
	}

	public ResponseEntity<Categoria> getById(Long id) {
		return repository.findById(id).map(categoria -> ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			carrinhoRepository.deleteByProdutoCategoriaId(id);
			produtoRepository.deleteByCategoriaId(id);
			repository.deleteById(id);
			return ResponseEntity.ok("Categoria removida");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover categoria");
		}

	}
	
	public Categoria alterarCategoria(Categoria categoriaAlterada, Long id) {
	    return repository.findById(id)
	            .map(categoria -> {
	                categoria.setDescricao(categoriaAlterada.getDescricao());
	                return repository.save(categoria);
	            })
	            .orElse(null);
	}
}
