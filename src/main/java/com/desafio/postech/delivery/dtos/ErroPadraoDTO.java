package com.desafio.postech.delivery.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ErroPadraoDTO(
		
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
		Instant horarioErro,
		
		Integer status,
		String erro,
		String messagemErro,
		String path
) {
}
