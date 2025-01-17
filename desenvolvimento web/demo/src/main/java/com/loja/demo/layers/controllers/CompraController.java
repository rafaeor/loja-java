package com.loja.demo.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.layers.entities.Compra;
import com.loja.demo.layers.services.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/{nomeCliente}")
    public List<Compra> obterComprasPorCliente(@PathVariable String nomeCliente) {
        return compraService.obterProdutos(nomeCliente);
    }
}