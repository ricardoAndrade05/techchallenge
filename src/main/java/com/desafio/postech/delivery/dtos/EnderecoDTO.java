package com.desafio.postech.delivery.dtos;

public record EnderecoDTO(
		String logradouro,
		String numero,
		String cidade,
		String estado,
		String cep
		) {

}
