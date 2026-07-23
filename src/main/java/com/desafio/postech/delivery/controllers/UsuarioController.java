package com.desafio.postech.delivery.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.postech.delivery.dtos.UsuarioAtualizaDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaSenhaDTO;
import com.desafio.postech.delivery.dtos.UsuarioConsultaDTO;
import com.desafio.postech.delivery.dtos.UsuarioDTO;
import com.desafio.postech.delivery.services.UsuarioService;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioConsultaDTO> getUsuarioById(@PathVariable Long id) {
		UsuarioConsultaDTO usuario =usuarioService.getUsuarioById(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioConsultaDTO> createUsuario(@RequestBody UsuarioDTO dto) {
		UsuarioConsultaDTO novoUsuario = usuarioService.novoUsuario(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.id()).toUri();
		return ResponseEntity.created(uri).body(novoUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioConsultaDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioAtualizaDTO dto){
		UsuarioConsultaDTO usuarioAtualizado = usuarioService.atualizaUsuario(id, dto);
		return ResponseEntity.ok().body(usuarioAtualizado);
	}
	
	@PutMapping("/atualiza-senha/{id}")
	public ResponseEntity<?> updateSenhaUsuario(@PathVariable Long id, @RequestBody UsuarioAtualizaSenhaDTO dto){
		usuarioService.atualizaSenha(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		usuarioService.excluiUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
