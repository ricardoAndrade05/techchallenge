package com.desafio.postech.delivery.dtos;

import com.desafio.postech.delivery.enums.TipoUsuario;

public record UsuarioConsultaDTO(
		Long id,
		String nome,
		String email,
		String login,
		TipoUsuario tipoUsuario,
		EnderecoDTO endereco
		) {

}
