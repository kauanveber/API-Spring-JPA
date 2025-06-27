package com.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.UsuarioRepository.PedidoRepository;
import com.api.UsuarioRepository.ProdutoRepository;
import com.api.modelos.Pedido;
import com.api.modelos.Produto;

@Service
public class PedidoService {
	private final PedidoRepository repository;
	private final ProdutoRepository produto;

    public PedidoService(PedidoRepository repository,ProdutoRepository produto) {
        this.repository = repository;
        this.produto = produto;
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getData() == null) {
            throw new RuntimeException("A data da venda deve ser informada");
        }
        
        List<Produto> produtosValidos = new ArrayList<>();
        for (Produto p : pedido.getProdutos()) {
            Produto produtoDb = produto.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Produto com ID " + p.getId() + " não existe no banco"));
            produtosValidos.add(produtoDb);
        }
        pedido.setProdutos(produtosValidos);
        return repository.save(pedido);
    }

    public List<Pedido> get() {
        return repository.findAll();
    }

    public ResponseEntity<Pedido> getById(Long id) {
        Optional<Pedido> pedidoOptional = repository.findById(id);
        return pedidoOptional.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("pedido removida com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover venda");
        }
    }

    public Pedido alterarPedido(Pedido pedidoAlterado, Long id) {
        return repository.findById(id)
            .map(pedido -> {
                pedido.setData(pedidoAlterado.getData());
                return repository.save(pedido);
            })
            .orElse(null);
    }
	
}
