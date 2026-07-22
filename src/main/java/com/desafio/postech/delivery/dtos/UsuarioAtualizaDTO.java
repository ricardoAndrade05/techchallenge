package com.desafio.postech.delivery.dtos;

public record UsuarioAtualizaDTO(
		String nome,
		String email,
		String login,
		Integer tipoUsuario,
		EnderecoDTO endereco
		) {

}
