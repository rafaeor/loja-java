package com.loja.demo.layers.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = false)
    private Double preco; // Preço

    @ManyToOne
    @JoinColumn(name = "desenvolvedor_id", nullable = false)
    private Desenvolvedor desenvolvedor;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void getDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }
}