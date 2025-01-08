package com.loja.demo.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.layers.entities.Compra;
import com.loja.demo.layers.repositories.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> obterProdutos(String nomeCliente) {
        return compraRepository.findComprasByClienteNome(nomeCliente);
    }

    public List<Object[]> obterProdutosEDataCompra(String nomeCliente) {
        return compraRepository.findProdutosAndDataCompraByClienteNome(nomeCliente);
    }

    public List<Compra> obterComprasPorClienteId(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }
}