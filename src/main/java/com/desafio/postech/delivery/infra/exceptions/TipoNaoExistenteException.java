package com.desafio.postech.delivery.infra.exceptions;

@SuppressWarnings("serial")
public class TipoNaoExistenteException extends RuntimeException {

	public TipoNaoExistenteException(String message) {
		super(message);
	}

}
