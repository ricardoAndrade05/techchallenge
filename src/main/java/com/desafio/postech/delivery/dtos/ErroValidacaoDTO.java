package com.desafio.postech.delivery.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacaoDTO extends ErroPadraoDTO {

	private List<CamposErro> erros = new ArrayList<>();
	
	public ErroValidacaoDTO(Instant horarioErro, Integer status, String erro,String path) {
		super(horarioErro,status,erro,path);
	}
	
	public List<CamposErro> getErros(){
		return this.erros;
	}
	
	public void addErros(String nomeCampo,String mensagem) {
		this.erros.removeIf(x-> x.nomeCampo().equals(nomeCampo));
		this.erros.add(new CamposErro(nomeCampo, mensagem));
	}
	
}
