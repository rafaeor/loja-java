package com.loja.demo.layers.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.entities.Cliente;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    @Query("SELECT c FROM Template c WHERE c.nome LIKE :nome")
    Optional<Template> getTemplatePorNome(@Param("nome") String nome);

    @Query("SELECT p FROM Cliente p WHERE p.nome LIKE %:nome%")
    List<Cliente> buscarPorNome(@Param("nome") String nome);
}