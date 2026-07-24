package com.desafio.postech.delivery.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.postech.delivery.dtos.LoginRequestDTO;
import com.desafio.postech.delivery.dtos.LoginResponseDTO;
import com.desafio.postech.delivery.entities.Usuario;
import com.desafio.postech.delivery.infra.exceptions.AutenticacaoException;
import com.desafio.postech.delivery.repositories.UsuarioRepository;

@Service
public class AutenticacaoService {

    @Value("${application.jwt.expiration}")
    private Long tempoExpiracao;
	
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    


    public AutenticacaoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        String emailLogin = dto.email().toLowerCase().trim();

        Usuario usuario = usuarioRepository.findByEmail(emailLogin).orElseThrow(() -> new AutenticacaoException("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new AutenticacaoException("Usuário ou senha inválidos.");
        }
        
        String token = tokenService.gerarToken(usuario);
        long tempoExpiracaoSegundos = 600L;

        return new LoginResponseDTO(token, tempoExpiracaoSegundos);
    }
}
