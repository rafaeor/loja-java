package com.loja.demo.layers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.repositories.TemplateRepository;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public Template cadastrarTemplate(Template template) throws ValidacaoException {
        templateRepository.save(template);
        return null;
    }
}
