package com.desafio.postech.delivery.dtos;

import com.desafio.postech.delivery.infra.validations.CepValido;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
		
		@NotBlank(message = "O logradouro é obrigatório.")
		@Size(min=5,max=10, message = "O logradouro deve conter entre 5 e 100 caracteres")
		String logradouro,
		
		@NotBlank(message = "O número é obrigatório.")
		String numero,
		
		@NotBlank(message = "A cidade é obrigatória.")
		@Size(min=2,max=100, message = "A cidade deve conter entre 5 e 100 caracteres")
		String cidade,
		
		@NotBlank(message = "O estado é obrigatório.")
		@Size(min=2,max=2, message = "Preencha utilizando a sigla do estado ex: São Paulo = SP")
		String estado,
		
		@NotBlank(message = "O cep é obrigatório.")
		@CepValido(message = "O cep deve ter apenas numeros")
		@Size(min = 8, max = 8, message = "O cep deve conter exatamente 8 caracteres")
		String cep
		) {
}
