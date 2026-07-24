package com.desafio.postech.delivery.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
		Long id,
		
		@NotBlank(message = "O nome é obrigatório.")
		String nome,
		
		@NotBlank(message = "O e-mail é obrigatório.")
		@Email(message = "O formato do e-mail é inválido")
		String email,
		
		@NotBlank(message = "O login é obrigatório.")
		String login,
		
		@NotBlank(message = "A senha é obrigatório.")
		@Size(min=5,max=10, message = "A senha deve conter entre 5 e 10 caracteres")
		String senha,
		
		@NotNull(message = "O tipo usuário é obrigatório.")
		Integer tipoUsuario,
		
		@Valid
		@NotNull(message = "O endereço é obrigatório.")
		EnderecoNovoDTO endereco
		) {
}
