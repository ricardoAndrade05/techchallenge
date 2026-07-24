package com.desafio.postech.delivery.infra.exceptions;

@SuppressWarnings("serial")
public class AutenticacaoException extends RuntimeException {
	
	public AutenticacaoException(String message) {
		super(message);
	}

}
