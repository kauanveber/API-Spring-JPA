package com.api.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.UsuarioRepository.CarrinhoRepository;
import com.api.UsuarioRepository.ProdutoRepository;
import com.api.UsuarioRepository.VendaRepository;
import com.api.modelos.Carrinho;
import com.api.modelos.Produto;
import com.api.modelos.Venda;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository, VendaRepository vendaRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
    }

    public Carrinho salvar(Carrinho carrinho) {

        // Valida produto
        Produto produto = carrinho.getProduto();
        if (produto == null || produto.getId() == null || !produtoRepository.existsById(produto.getId())) {
            throw new RuntimeException("Produto inválido ou inexistente");
        }

        // Valida venda
        Venda venda = carrinho.getVenda();
        if (venda == null || venda.getId() == null || !vendaRepository.existsById(venda.getId())) {
            throw new RuntimeException("Venda inválida ou inexistente");
        }

        // Valida quantidade
        if (carrinho.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        // Valida preço
        if (carrinho.getPrecoItem() < 0) {
            throw new RuntimeException("Preço do item não pode ser negativo");
        }

        return carrinhoRepository.save(carrinho);
    }

    public List<Carrinho> get() {
        return carrinhoRepository.findAll();
    }

    public ResponseEntity<Carrinho> getById(Long id) {
        return carrinhoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> deletar(Long id) {
        if (carrinhoRepository.existsById(id)) {
            carrinhoRepository.deleteById(id);
            return ResponseEntity.ok("Carrinho removido com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não encontrado");
        }
    }

    public Carrinho alterarCarrinho(Carrinho carrinhoAtualizado, Long id) {
        return carrinhoRepository.findById(id)
                .map(carrinho -> {
                    carrinho.setProduto(carrinhoAtualizado.getProduto());
                    carrinho.setVenda(carrinhoAtualizado.getVenda());
                    carrinho.setPrecoItem(carrinhoAtualizado.getPrecoItem());
                    carrinho.setQuantidade(carrinhoAtualizado.getQuantidade());
                    return carrinhoRepository.save(carrinho);
                })
                .orElse(null);
    }
}
