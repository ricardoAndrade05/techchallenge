package com.desafio.postech.delivery.dtos;

import com.desafio.postech.delivery.infra.validations.TipoUsuarioValido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizaDTO(
	
		@NotBlank(message = "O nome é obrigatório.")
		@Size(min=5,max=100, message = "O nome deve conter entre 2 e 100 caracteres")
		String nome,
		
		@NotBlank(message = "O e-mail é obrigatório.")
		@Email(message = "O formato do e-mail é inválido")
		String email,
		
		@NotBlank(message = "O login é obrigatório.")
		@Size(min=5,max=100, message = "O login deve conter entre 2 e 100 caracteres")
		String login,
		
		@NotNull(message = "O tipo usuário é obrigatório.")
		@TipoUsuarioValido(message = "Tipo de usuário inválido")
		Integer tipoUsuario,
		
		@Valid
		@NotNull(message = "O endereço é obrigatório.")
		EnderecoDTO endereco
		) {

}
