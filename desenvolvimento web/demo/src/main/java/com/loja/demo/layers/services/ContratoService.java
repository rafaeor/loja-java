package com.loja.demo.layers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Contrato;
import com.loja.demo.layers.repositories.ContratoRepository;

@Service
public class ContratoService {
    @Autowired
    ContratoRepository contratoRepository;

    public Contrato cadastrarContrato(Contrato contrato) throws ValidacaoException {
        contratoRepository.save(contrato);
        return null;
    }
}
