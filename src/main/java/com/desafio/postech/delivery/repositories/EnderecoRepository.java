package com.desafio.postech.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.postech.delivery.entities.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
