package com.desafio.postech.delivery.infra.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<CepValido, String> {

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        // Deixa a responsabilidade de barrar para o @NotBlank/@NotNull
        if (cep == null || cep.isBlank()) {
            return true;
        }
        
        String cepApenasNumeros = cep.replace("-", "").trim();
        
        return cepApenasNumeros.length() == 8 && cepApenasNumeros.matches("\\d+");
    }
}