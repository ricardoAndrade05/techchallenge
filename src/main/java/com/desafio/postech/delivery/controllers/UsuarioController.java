package com.desafio.postech.delivery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.postech.delivery.entities.Usuario;
import com.desafio.postech.delivery.services.UsuarioService;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
		Usuario usuario =usuarioService.getUsuarioById(id);
		return ResponseEntity.ok().body(usuario);
	}
	

}
