package com.loja.demo.layers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Desenvolvedor;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {

    @Query("SELECT c FROM Desenvolvedor c WHERE c.nome LIKE :nome")
    Desenvolvedor getDesenvolvedorPorNome(String nome);

    @Query("SELECT p FROM Desenvolvedor p WHERE p.nome LIKE %:nome%")
    List<Desenvolvedor> buscarPorNome(@Param("nome") String nome);
}
