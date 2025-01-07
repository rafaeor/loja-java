package com.loja.demo.layers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.repositories.TemplateRepository;

@Service
public class ContratoService {
    @Autowired
    TemplateRepository produtoRepository;

    public Template cadastrarProduto(Template produto) throws ValidacaoException {
        produtoRepository.save(produto);
        return null;
    }
}
