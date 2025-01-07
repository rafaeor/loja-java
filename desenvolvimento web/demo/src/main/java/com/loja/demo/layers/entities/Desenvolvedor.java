package com.loja.demo.layers.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Desenvolvedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    // Relação 1:N com Compra
    @OneToMany(mappedBy = "desenvolvedor", cascade = CascadeType.ALL)
    private List<Template> produtos;

    // Relação 1:N com Compra
    @OneToMany(mappedBy = "desenvolvedor", cascade = CascadeType.ALL)
    private List<Template> contratos;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Template> getTemplates() {
        return produtos;
    }

    public void setTemplates(List<Template> produtos) {
        this.produtos = produtos;
    }

    public List<Template> getContratos() {
        return produtos;
    }

    public void setContratos(List<Template> produtos) {
        this.produtos = produtos;
    }
}
