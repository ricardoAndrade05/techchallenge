package com.desafio.postech.delivery.dtos;

import jakarta.validation.constraints.NotBlank;

public record EnderecoNovoDTO(
		
		@NotBlank(message = "O logradouro é obrigatório.")
		String logradouro,
		
		@NotBlank(message = "O número é obrigatório.")
		String numero,
		
		@NotBlank(message = "A cidade é obrigatória.")
		String cidade,
		
		@NotBlank(message = "O estado é obrigatório.")
		String estado,
		
		@NotBlank(message = "O cep é obrigatório.")
		String cep
		) {
}
