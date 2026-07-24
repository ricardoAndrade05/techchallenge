package com.desafio.postech.delivery.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizaSenhaDTO(
		@NotBlank(message = "O senha atual é obrigatória.")
		String senhaAtual,
		
		@NotBlank(message = "O nova senha é obrigatória.")
		@Size(min=5,max=10, message = "A nova senha deve conter entre 5 e 10 caracteres")
		String senhaNova
		) {
}
