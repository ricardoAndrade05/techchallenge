package com.desafio.postech.delivery.infra.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio.postech.delivery.dtos.ErroPadraoDTO;
import com.desafio.postech.delivery.infra.exceptions.RecursoNaoEncontradoException;
import com.desafio.postech.delivery.infra.exceptions.RegraDeNegociosException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerHandler {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroPadraoDTO> handlerRecursoNaoEncontradoException(RecursoNaoEncontradoException ex,HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String erro = "Recurso não encontrado.";
		ErroPadraoDTO erroPadraoDTO = montaErroPadrao(status, erro, ex, request);
		return ResponseEntity.status(status).body(erroPadraoDTO);
	}
	
	@ExceptionHandler(RegraDeNegociosException.class)
	public ResponseEntity<ErroPadraoDTO> handlerRegraDeNegociosException(RegraDeNegociosException ex,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String erro = "Regra de Negócios.";
		ErroPadraoDTO erroPadraoDTO = montaErroPadrao(status, erro, ex, request);
		return ResponseEntity.status(status).body(erroPadraoDTO);
	}
	
	private ErroPadraoDTO montaErroPadrao(HttpStatus status,String erro,RuntimeException ex,HttpServletRequest request) {
		Instant horarioErro = Instant.now();
		String mensagemErro = ex.getMessage();
		String pathErro = request.getRequestURI();
		ErroPadraoDTO erroPadraoDTO = new ErroPadraoDTO(horarioErro, status.value(), erro, mensagemErro, pathErro);
		return erroPadraoDTO;
	}
}
