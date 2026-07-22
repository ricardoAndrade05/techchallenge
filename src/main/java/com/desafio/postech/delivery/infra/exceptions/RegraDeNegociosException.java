package com.desafio.postech.delivery.infra.exceptions;

@SuppressWarnings("serial")
public class RegraDeNegociosException extends RuntimeException {
	
	public RegraDeNegociosException(String message) {
		super(message);
	}

}
