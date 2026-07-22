package com.desafio.postech.delivery.enums;

import com.desafio.postech.delivery.infra.exceptions.RecursoNaoEncontradoException;

public enum TipoUsuario {
	
	DONO(1, "Dono"),
	CLIENTE(2, "Cliente");
	
	private Integer codigo;
	private String descricao;
	
	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoUsuario fromCodigo(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		throw new RecursoNaoEncontradoException("Código de tipo de usuário inválido: " + codigo);
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
