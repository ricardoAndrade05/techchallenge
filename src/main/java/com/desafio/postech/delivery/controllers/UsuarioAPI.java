package com.desafio.postech.delivery.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.desafio.postech.delivery.dtos.ErroPadraoDTO;
import com.desafio.postech.delivery.dtos.ErroValidacaoDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaSenhaDTO;
import com.desafio.postech.delivery.dtos.UsuarioConsultaDTO;
import com.desafio.postech.delivery.dtos.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public interface UsuarioAPI {
	
	@Operation(summary = "Usuário logado", description = "Recupera as informações do usuário que esta logado.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "200", 
        		description = "Retorna dados do usuario logado com sucesso."),
        @ApiResponse(
        		responseCode = "401", 
        		description = "É necessário estar logado para acessar este recurso.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class)))
    })
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioConsultaDTO> getMeuPerfil(@AuthenticationPrincipal Jwt jwt);
	
	@Operation(summary = "Recupera Usuário", description = "Dado um id, recupera o respecitvo usuario com suas informações.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "200",
        		description = "Usuário retornado com suceso."),
        @ApiResponse(
        		responseCode = "401",
        		description = "É necessário estar logado para acessar este recurso.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "404",
        		description = "Usuário inexistem no banco.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class)))
    })
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UsuarioConsultaDTO> buscaUsuarioPorId(@PathVariable Long id);
	
	@Operation(summary = "Busca Usuário(s)", description = "Recebe um nome como parametro e lista os usuarios com o respecitvo nome.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "200",
        		description = "Usuário atualizado com sucesso."),
        @ApiResponse(
        		responseCode = "401",
        		description = "É necessário estar logado para acessar este recurso.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class)))
    })
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<UsuarioConsultaDTO>> buscaUsuariosPorNome(
			@RequestParam(defaultValue = "") String nome, 
			@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)Pageable pageable);
	
	@Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário com endereço associado")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "201",
        		description = "Usuário criado com sucesso."),
        @ApiResponse(
        		responseCode = "422",
        		description = "Dados de entrada inválidos.",
        		content = @Content(schema = @Schema(implementation = ErroValidacaoDTO.class)))
    })
	public ResponseEntity<UsuarioConsultaDTO> createUsuario(@Valid @RequestBody UsuarioDTO dto);
	
	@Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuario, exceto sua senha.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "200",
        		description = "Usuário atualizado com sucesso."),
        @ApiResponse(
        		responseCode = "401",
        		description = "É necessário estar logado para acessar este recurso.",
                content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "404",
        		description = "Usuário inexistente no banco.",
                content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "422",
        		description = "Dados de entrada inválidos.",
        		content = @Content(schema = @Schema(implementation = ErroValidacaoDTO.class)))
    })
	public ResponseEntity<UsuarioConsultaDTO> updateUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioAtualizaDTO dto);
	
	@Operation(summary = "Atualizar senha", description = "Atualiza a senha de um usuário.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "204",
        		description = "Senha atualizada com sucesso."),
        @ApiResponse(
        		responseCode = "401",
        		description = "É necessário estar logado para acessar este recurso.",
                content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "400",
        		description = "Senha atual invalida.",
                content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "404",
        		description = "Usuário inexistem no banco.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "422",
        		description = "Dados de entrada inválidos.",
        		content = @Content(schema = @Schema(implementation = ErroValidacaoDTO.class)))
    })
	public ResponseEntity<?> updateSenhaUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioAtualizaSenhaDTO dto);
	
	@Operation(summary = "Excluir usuário", description = "Passado um id, ele exclui o respectivo usuário.")
    @ApiResponses(value = {
        @ApiResponse(
        		responseCode = "204",
        		description = "Usuário excluido com sucesso."),
        @ApiResponse(
        		responseCode = "401",
        		description = "É necessário estar logado para acessar este recurso.",
                content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class))),
        @ApiResponse(
        		responseCode = "404",
        		description = "Usuário inexistem no banco.",
        		content = @Content(schema = @Schema(implementation = ErroPadraoDTO.class)))
    })
	public ResponseEntity<Void> deleteUsuario(@PathVariable Long id);

}
