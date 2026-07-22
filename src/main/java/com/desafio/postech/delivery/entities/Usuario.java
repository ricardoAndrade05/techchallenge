package com.desafio.postech.delivery.entities;

import java.time.LocalDateTime;

import com.desafio.postech.delivery.enums.TipoUsuario;
import com.desafio.postech.delivery.infra.converters.TipoUsuarioConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String login;
	private String senha;
	private LocalDateTime dataUltimaAlteracao;
	
	@Convert(converter = TipoUsuarioConverter.class)
	private TipoUsuario tipoUsuario;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
	private Endereco endereco;
	
	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public void setDataUltimaAlteracao(LocalDateTime dataAlteracao) {
		this.dataUltimaAlteracao = dataAlteracao;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
