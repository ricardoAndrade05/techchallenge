package com.desafio.postech.delivery.services;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.postech.delivery.dtos.EnderecoDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaDTO;
import com.desafio.postech.delivery.dtos.UsuarioAtualizaSenhaDTO;
import com.desafio.postech.delivery.dtos.UsuarioConsultaDTO;
import com.desafio.postech.delivery.dtos.UsuarioDTO;
import com.desafio.postech.delivery.entities.Endereco;
import com.desafio.postech.delivery.entities.Usuario;
import com.desafio.postech.delivery.enums.TipoUsuario;
import com.desafio.postech.delivery.infra.exceptions.RecursoNaoEncontradoException;
import com.desafio.postech.delivery.infra.exceptions.RegraDeNegociosException;
import com.desafio.postech.delivery.infra.mappers.UsuarioMapper;
import com.desafio.postech.delivery.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
	private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder; 

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public UsuarioConsultaDTO getUsuarioById(Long id) {
        return usuarioMapper.toDTO(usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario inexistente")));
    }

    @Transactional
    public UsuarioConsultaDTO novoUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioConsultaDTO atualizaUsuario(Long id, UsuarioAtualizaDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario inexistente"));
        atualizaCampos(usuario, usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
    
    @Transactional
    public void excluiUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario inexistente"));
        usuarioRepository.delete(usuario);
    }
    
    public void atualizaSenha(Long id, UsuarioAtualizaSenhaDTO dto) {
    	Usuario usuario =usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuario inexistente"));
    	boolean senhaValida = passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha());
    	if (senhaValida) {
    		usuario.setDataUltimaAlteracao(LocalDateTime.now());
            String novaSenhaCriptografada = passwordEncoder.encode(dto.senhaNova());
            usuario.setSenha(novaSenhaCriptografada);
            usuarioRepository.save(usuario);
    	}else {
    		throw new RegraDeNegociosException("Senha atual inválida.");
    	}
	}

	private void atualizaCampos(Usuario usuario, UsuarioAtualizaDTO dto) {
        boolean teveAtualizacao = false;
        teveAtualizacao |= atualizarSeAlterado(dto.nome(), usuario::getNome, usuario::setNome);
        teveAtualizacao |= atualizarSeAlterado(dto.email(), usuario::getEmail, usuario::setEmail);
        teveAtualizacao |= atualizarSeAlterado(dto.login(), usuario::getLogin, usuario::setLogin);
        if (dto.tipoUsuario() != null && usuario.getTipoUsuario().getCodigo() != dto.tipoUsuario()) {
            usuario.setTipoUsuario(TipoUsuario.fromCodigo(dto.tipoUsuario()));
            teveAtualizacao = true;
        }
        EnderecoDTO endDto = dto.endereco();
        Endereco endereco = usuario.getEndereco();
        if (endDto != null && endereco != null) {
            teveAtualizacao |= atualizarSeAlterado(endDto.logradouro(), endereco::getLogradouro, endereco::setLogradouro);
            teveAtualizacao |= atualizarSeAlterado(endDto.numero(), endereco::getNumero, endereco::setNumero);
            teveAtualizacao |= atualizarSeAlterado(endDto.cidade(), endereco::getCidade, endereco::setCidade);
            teveAtualizacao |= atualizarSeAlterado(endDto.estado(), endereco::getEstado, endereco::setEstado);
            teveAtualizacao |= atualizarSeAlterado(endDto.cep(), endereco::getCep, endereco::setCep);
        }
        if (teveAtualizacao) {
            usuario.setDataUltimaAlteracao(LocalDateTime.now());
        } else {
            throw new RegraDeNegociosException("Não há dados alterados, ou as informações enviadas já são as atuais.");
        }
    }

    private boolean atualizarSeAlterado(String novoValor, Supplier<String> obterValorAtual, Consumer<String> definirNovoValor) {
        if (novoValor != null && !novoValor.equalsIgnoreCase(obterValorAtual.get())) {
            definirNovoValor.accept(novoValor);
            return true;
        }
        return false;
    }
}