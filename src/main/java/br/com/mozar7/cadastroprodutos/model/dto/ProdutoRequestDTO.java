package br.com.mozar7.cadastroprodutos.model.dto;

import org.w3c.dom.Text;

import java.math.BigDecimal;

public record ProdutoRequestDTO(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Boolean ativo) {
}
