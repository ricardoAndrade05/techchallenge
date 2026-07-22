package com.desafio.postech.delivery.infra.exceptions;

@SuppressWarnings("serial")
public class RecursoNaoEncontradoException extends RuntimeException {

	public RecursoNaoEncontradoException(String message) {
		super(message);
	}

}
