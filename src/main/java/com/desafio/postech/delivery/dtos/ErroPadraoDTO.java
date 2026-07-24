package com.desafio.postech.delivery.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroPadraoDTO{
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Instant horarioErro;
	
	private Integer status;
	private String erro;
	private String messagemErro;
	private String path;
	
	public ErroPadraoDTO() {
		super();
	}

	public ErroPadraoDTO(Instant horarioErro, Integer status, String erro, String messagemErro, String path) {
		super();
		this.horarioErro = horarioErro;
		this.status = status;
		this.erro = erro;
		this.messagemErro = messagemErro;
		this.path = path;
	}
	
	public ErroPadraoDTO(Instant horarioErro, Integer status, String erro, String path) {
		super();
		this.horarioErro = horarioErro;
		this.status = status;
		this.erro = erro;
		this.path = path;
	}

	public Instant getHorarioErro() {
		return horarioErro;
	}

	public Integer getStatus() {
		return status;
	}

	public String getErro() {
		return erro;
	}

	public String getMessagemErro() {
		return messagemErro;
	}

	public String getPath() {
		return path;
	}
}
