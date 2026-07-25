package com.desafio.postech.delivery.infra.validations;

import com.desafio.postech.delivery.enums.TipoUsuario;
import com.desafio.postech.delivery.infra.exceptions.RecursoNaoEncontradoException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoUsuarioValidator implements ConstraintValidator<TipoUsuarioValido, Integer>{
	
	@Override
    public boolean isValid(Integer tipoUsuario, ConstraintValidatorContext context) {
        // Deixa a responsabilidade de barrar para o @NotNull
        if (tipoUsuario == null) {
            return true;
        }
        try {
			TipoUsuario.fromCodigo(tipoUsuario);
			return Boolean.TRUE;
		} catch (RecursoNaoEncontradoException e) {
			return false;
		}
      
    }

}
