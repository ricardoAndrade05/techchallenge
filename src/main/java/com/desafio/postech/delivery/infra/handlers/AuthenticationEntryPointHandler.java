package com.desafio.postech.delivery.infra.handlers;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.desafio.postech.delivery.dtos.ErroPadraoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

    public AuthenticationEntryPointHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErroPadraoDTO erroDto = new ErroPadraoDTO(Instant.now(),
        		HttpStatus.UNAUTHORIZED.value(),
        		"Não Autorizado",
        		"É necessário estar logado para acessar este recurso.",
        		request.getRequestURI());
        String jsonResposta = objectMapper.writeValueAsString(erroDto);
        response.getWriter().write(jsonResposta);
    }
}