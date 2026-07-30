package com.desafio.postech.delivery.controllers;


import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.postech.delivery.dtos.UsuarioAtualizaDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaSenhaDTO;
import com.desafio.postech.delivery.dtos.UsuarioConsultaDTO;
import com.desafio.postech.delivery.dtos.UsuarioDTO;
import com.desafio.postech.delivery.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {
	
	private final UsuarioService usuarioService;

	UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Operation(summary = "Usuário logado", description = "Recupera as informações do usuário que esta logado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna dados do usuario logado com sucesso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
    })
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/me")
    public ResponseEntity<UsuarioConsultaDTO> getMeuPerfil(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        UsuarioConsultaDTO dto = usuarioService.buscarPorEmail(email); 
        return ResponseEntity.ok(dto);
    }
	
	@Operation(summary = "Recupera Usuário", description = "Dado um id, recupera o respecitvo usuario com suas informações.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário retornado com suceso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
        @ApiResponse(responseCode = "404", description = "Usuário inexistem no banco.")
    })
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioConsultaDTO> buscaUsuarioPorId(@PathVariable Long id) {
		UsuarioConsultaDTO usuario = usuarioService.recuperaUsuarioPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@Operation(summary = "Busca Usuário(s)", description = "Recebe um nome como parametro e lista os usuarios com o respecitvo nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
    })
	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity<Page<UsuarioConsultaDTO>> buscaUsuariosPorNome(
			@RequestParam(defaultValue = "") String nome, 
			@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)Pageable pageable) {
		Page<UsuarioConsultaDTO> usuarios = usuarioService.buscaUsuariosPorNome(nome, pageable);
		return ResponseEntity.ok().body(usuarios);
	}
	
	@Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário com endereço associado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.")
    })
	@PostMapping
	public ResponseEntity<UsuarioConsultaDTO> createUsuario(@Valid @RequestBody UsuarioDTO dto) {
		UsuarioConsultaDTO novoUsuario = usuarioService.novoUsuario(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.id()).toUri();
		return ResponseEntity.created(uri).body(novoUsuario);
	}
	
	@Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuario, exceto sua senha.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
        @ApiResponse(responseCode = "404", description = "Usuário inexistem no banco."),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.")
    })
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioConsultaDTO> updateUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioAtualizaDTO dto){
		UsuarioConsultaDTO usuarioAtualizado = usuarioService.atualizaUsuario(id, dto);
		return ResponseEntity.ok().body(usuarioAtualizado);
	}
	
	
	@Operation(summary = "Atualizar senha", description = "Atualiza a senha de um usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
        @ApiResponse(responseCode = "400", description = "Senha atual invalida."),
        @ApiResponse(responseCode = "404", description = "Usuário inexistem no banco."),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.")
    })
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/atualiza-senha/{id}")
	public ResponseEntity<?> updateSenhaUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioAtualizaSenhaDTO dto){
		usuarioService.atualizaSenha(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Excluir usuário", description = "Passado um id, ele exclui o respectivo usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário excluido com sucesso."),
        @ApiResponse(responseCode = "401", description = "É necessário estar logado para acessar este recurso."),
        @ApiResponse(responseCode = "404", description = "Usuário inexistem no banco.")
    })
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
		usuarioService.excluiUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
