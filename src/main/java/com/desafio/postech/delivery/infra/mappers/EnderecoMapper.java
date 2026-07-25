package com.desafio.postech.delivery.infra.mappers;

import org.mapstruct.Mapper;

import com.desafio.postech.delivery.dtos.EnderecoDTO;
import com.desafio.postech.delivery.entities.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
	
	Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDTO(Endereco endereco);

}
