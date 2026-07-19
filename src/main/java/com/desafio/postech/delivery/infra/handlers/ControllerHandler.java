package com.desafio.postech.delivery.infra.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.desafio.postech.delivery.dtos.TipoNaoExistenteDTO;
import com.desafio.postech.delivery.infra.exceptions.TipoNaoExistenteException;

@ControllerAdvice
public class ControllerHandler {
	
	public ResponseEntity<TipoNaoExistenteDTO> handlerTipoNaoExistenteException(TipoNaoExistenteException ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoNaoExistenteDTO errorResponse = new TipoNaoExistenteDTO(ex.getMessage(), status.value());
		return ResponseEntity.status(status).body(errorResponse);
	}

}
