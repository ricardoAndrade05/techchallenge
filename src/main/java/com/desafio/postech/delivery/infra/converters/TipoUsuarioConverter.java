package com.desafio.postech.delivery.infra.converters;

import com.desafio.postech.delivery.enums.TipoUsuario;
import com.desafio.postech.delivery.infra.exceptions.RecursoNaoEncontradoException;

import jakarta.persistence.AttributeConverter;

public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoUsuario tipoUsuario) {
		if (tipoUsuario == null) {
			throw new RecursoNaoEncontradoException("Tipo usuario inexistente");
		}
		return tipoUsuario.getCodigo();
	}

	@Override
	public TipoUsuario convertToEntityAttribute(Integer codigoUsuario) {
		if (codigoUsuario == null) {
			throw new RecursoNaoEncontradoException("Tipo usuario inexistente");
		}
		return TipoUsuario.fromCodigo(codigoUsuario);
	}

}
