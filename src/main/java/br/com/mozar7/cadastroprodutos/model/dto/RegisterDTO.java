package br.com.mozar7.cadastroprodutos.model.dto;

import br.com.mozar7.cadastroprodutos.model.user.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {
}
