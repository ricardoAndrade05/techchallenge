package com.desafio.postech.delivery.infra.handlers;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.desafio.postech.delivery.dtos.ErroPadraoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public SecurityExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        escreverRespostaErro(request, response, HttpStatus.UNAUTHORIZED, "Não Autorizado", "É necessário estar logado para acessar este recurso.");
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        escreverRespostaErro(request, response, HttpStatus.FORBIDDEN, "Acesso Negado", "Você não possui permissão para acessar este recurso.");
    }

    private void escreverRespostaErro(HttpServletRequest request, HttpServletResponse response, HttpStatus status, String tituloErro, String mensagem) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        ErroPadraoDTO erroDto = new ErroPadraoDTO(
                Instant.now(),
                status.value(),
                tituloErro,
                mensagem,
                request.getRequestURI()
        );

        String jsonResposta = objectMapper.writeValueAsString(erroDto);
        response.getWriter().write(jsonResposta);
    }
}