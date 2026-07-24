package com.desafio.postech.delivery.services;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.desafio.postech.delivery.entities.Usuario;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();
        long tempoExpiracao = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("tech-challenge-delivery") 
                .subject(usuario.getLogin()) 
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(tempoExpiracao))
                .claim("email", usuario.getEmail())
                .claim("role", usuario.getTipoUsuario().name()) 
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}