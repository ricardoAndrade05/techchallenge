package com.desafio.postech.delivery.infra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.desafio.postech.delivery.dtos.UsuarioConsultaDTO;
import com.desafio.postech.delivery.dtos.UsuarioDTO;
import com.desafio.postech.delivery.entities.Usuario;
import com.desafio.postech.delivery.enums.TipoUsuario;

@Mapper(
	    componentModel = "spring",
	    unmappedTargetPolicy = ReportingPolicy.IGNORE,
	    uses = { EnderecoMapper.class }
	)
	public interface UsuarioMapper {

	    @Mapping(target = "dataUltimaAlteracao", ignore = true)
	    @Mapping(target = "tipoUsuario", source = "tipoUsuario", qualifiedByName = "codigoParaTipoUsuario")
	    Usuario toEntity(UsuarioDTO usuarioDTO);

	    UsuarioConsultaDTO toDTO(Usuario usuario);

	    @Named("codigoParaTipoUsuario")
	    default TipoUsuario codigoParaTipoUsuario(Integer codigo) {
	        if (codigo == null) {
	            return null;
	        }
	        return TipoUsuario.fromCodigo(codigo);
	    }
	}
