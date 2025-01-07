package com.loja.demo.layers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.layers.repositories.CompraRepository;

@Service
public class TransacaoService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Object[]> obterProdutosEDataCompra(String nomeCliente) {
        return compraRepository.findProdutosAndDataCompraByClienteNome(nomeCliente);
    }
}