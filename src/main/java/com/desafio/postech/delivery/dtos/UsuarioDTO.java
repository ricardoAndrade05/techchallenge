package com.desafio.postech.delivery.dtos;

public record UsuarioDTO(
		Long id,
		String nome,
		String email,
		String login,
		String senha,
		Integer tipoUsuario,
		EnderecoDTO endereco
		) {

}
