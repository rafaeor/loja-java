package com.loja.demo.layers.entities;

import java.util.Date;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataInicio; // Data de início

    @Column(nullable = true)
    private Date dataFim; // Data de fim

    @Column(nullable = true)
    private Double preco; // Preço

    @Column(nullable = false, unique = true)
    private String nome;

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void getNome(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Optional<Desenvolvedor> getDesenvolvedor() {
        return Optional.ofNullable(desenvolvedor);
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

}