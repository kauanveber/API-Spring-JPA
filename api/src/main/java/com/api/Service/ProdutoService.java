package com.api.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.UsuarioRepository.CategoriaRepository;
import com.api.UsuarioRepository.ProdutoRepository;
import com.api.modelos.Categoria;
import com.api.modelos.Produto;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriasRepository;

    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriasRepository) {
        this.repository = repository;
        this.categoriasRepository = categoriasRepository;
    }

    public Produto salvar(Produto produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            throw new RuntimeException("Descrição inválida");
        }

        if (produto.getPreco() < 0) {
            throw new RuntimeException("Valor do produto inválido");
        }

        if (produto.getEstoque() <= 0) {
            throw new RuntimeException("Estoque inválido");
        }

       
        Long categoriaId = produto.getCategoria() != null ? produto.getCategoria().getId() : null;
        if (categoriaId == null || !categoriasRepository.existsById(categoriaId)) {
            throw new RuntimeException("Categoria inválida");
        }

        return repository.save(produto);
    }

    public List<Produto> get() {
        return repository.findAll();
    }

    public ResponseEntity<Produto> getById(Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Produto removido");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover produto");
        }
    }

    public Produto alteraProduto(Produto produtoAlterado, Long id) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setDescricao(produtoAlterado.getDescricao());
                    produto.setPreco(produtoAlterado.getPreco());
                    produto.setEstoque(produtoAlterado.getEstoque());
                    produto.setCategoria(produtoAlterado.getCategoria());
                    return repository.save(produto);
                })
                .orElse(null);
    }
}
