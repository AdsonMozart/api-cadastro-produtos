package br.com.mozar7.cadastroprodutos.repository;

import br.com.mozar7.cadastroprodutos.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
