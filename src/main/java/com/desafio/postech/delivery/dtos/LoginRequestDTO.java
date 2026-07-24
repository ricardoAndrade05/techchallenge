package com.desafio.postech.delivery.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "O e-mail/login é obrigatório")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    String senha
) {}
