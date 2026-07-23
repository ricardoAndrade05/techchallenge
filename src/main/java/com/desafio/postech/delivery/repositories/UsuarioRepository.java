package com.desafio.postech.delivery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.desafio.postech.delivery.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);
	
	@Query("SELECT usuario "
		 + "FROM Usuario usuario "
		 + "WHERE UPPER(usuario.nome) LIKE UPPER(CONCAT('%',:nome,'%')) ")
	Page<Usuario> buscaUsuariosPorNome(String nome, Pageable pageable);
	
}
