package com.desafio.postech.delivery.infra.mappers;

import org.mapstruct.Mapper;

import com.desafio.postech.delivery.dtos.EnderecoNovoDTO;
import com.desafio.postech.delivery.entities.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
	
	Endereco toEntity(EnderecoNovoDTO dto);

    EnderecoNovoDTO toDTO(Endereco endereco);

}
