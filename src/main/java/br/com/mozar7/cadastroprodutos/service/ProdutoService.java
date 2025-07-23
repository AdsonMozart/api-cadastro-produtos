package br.com.mozar7.cadastroprodutos.service;

import br.com.mozar7.cadastroprodutos.model.dto.ProdutoRequestDTO;
import br.com.mozar7.cadastroprodutos.model.produto.Produto;
import br.com.mozar7.cadastroprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> listarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Optional<Produto> atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> optProduto = produtoRepository.findById(id);
        if (optProduto.isPresent()) {
            Produto produto = optProduto.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
            produto.setAtivo(produtoAtualizado.getAtivo());

            produtoRepository.save(produto);
            return Optional.of(produto);
        }
        return Optional.empty();
    }
}
