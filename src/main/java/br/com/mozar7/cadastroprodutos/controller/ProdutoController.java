package br.com.mozar7.cadastroprodutos.controller;

import br.com.mozar7.cadastroprodutos.model.dto.ProdutoRequestDTO;
import br.com.mozar7.cadastroprodutos.model.produto.Produto;
import br.com.mozar7.cadastroprodutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    };

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    };

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Long id) {
        return produtoService.listarProdutoPorId(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        Produto produto = produtoService.listarProdutoPorId(id).orElse(null);
        if (produto != null) {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProdutoPorId(@PathVariable Long id, @PathVariable Produto produtoAtualizado) {
        return produtoService.atualizarProduto(id, produtoAtualizado)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }
}
