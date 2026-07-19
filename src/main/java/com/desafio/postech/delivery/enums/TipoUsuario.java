package com.desafio.postech.delivery.enums;

import com.desafio.postech.delivery.infra.exceptions.TipoNaoExistenteException;

public enum TipoUsuario {
	
	DONO(1, "Dono"),
	CLIENTE(2, "Cliente");
	
	private int codigo;
	private String descricao;
	
	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoUsuario fromCodigo(int codigo) {
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (tipo.getCodigo() == codigo) {
				return tipo;
			}
		}
		throw new TipoNaoExistenteException("Código de tipo de usuário inválido: " + codigo);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	

}
