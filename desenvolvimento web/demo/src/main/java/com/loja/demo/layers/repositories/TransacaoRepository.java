package com.loja.demo.layers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
