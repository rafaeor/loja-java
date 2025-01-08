package com.loja.demo.layers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    @Query("SELECT c FROM Contrato c WHERE c.nome LIKE :nome")
    Optional<Contrato> getPorNome(@Param("nome") String nome);

}
