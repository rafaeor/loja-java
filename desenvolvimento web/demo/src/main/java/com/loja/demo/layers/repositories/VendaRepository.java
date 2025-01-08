package com.loja.demo.layers.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByDataBetween(Date startDate, Date endDate);

    List<Venda> findByTipo(String tipo);

    @Query("SELECT c FROM Venda c WHERE c.desenvolvedor.id = :desenvolvedorId")
    List<Venda> findByDesenvolvedorId(@Param("desenvolvedorId") Long desenvolvedorId);
}
