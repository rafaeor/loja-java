package com.loja.demo.layers.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    Cliente getClientePorNome(String nome);

    @Query("SELECT p FROM Cliente p WHERE p.nome LIKE %:nome%")
    List<Cliente> buscarPorNome(@Param("nome") String nome);
}
