package com.desafio.postech.delivery.dtos;

import java.time.LocalDateTime;

import com.desafio.postech.delivery.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;

public record UsuarioConsultaDTO(
		Long id,
		String nome,
		String email,
		String login,
		TipoUsuario tipoUsuario,
		
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
		LocalDateTime dataUltimaAlteracao,
		EnderecoDTO endereco
		) {

}
