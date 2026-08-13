package com.desafio.postech.delivery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.postech.delivery.dtos.LoginRequestDTO;
import com.desafio.postech.delivery.dtos.LoginResponseDTO;
import com.desafio.postech.delivery.services.AutenticacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacao")
public class AutenticacaoController implements AutenticacaoAPI {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        LoginResponseDTO response = autenticacaoService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
