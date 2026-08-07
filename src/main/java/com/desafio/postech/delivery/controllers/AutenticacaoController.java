package com.desafio.postech.delivery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.postech.delivery.dtos.ErroPadraoDTO;
import com.desafio.postech.delivery.dtos.LoginRequestDTO;
import com.desafio.postech.delivery.dtos.LoginResponseDTO;
import com.desafio.postech.delivery.services.AutenticacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @Operation(summary = "Login", description = "Realiza o login do usuario")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Usuario logado com sucesso."),
        @ApiResponse(
            responseCode = "401", 
            description = "Usuário ou senha inválidos.",
            content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class)))
    })
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        LoginResponseDTO response = autenticacaoService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
