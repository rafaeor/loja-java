package com.loja.demo.layers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Desenvolvedor;
import com.loja.demo.layers.entities.Venda;
import com.loja.demo.layers.repositories.DesenvolvedorRepository;
import com.loja.demo.layers.repositories.VendaRepository;

@Service
public class DesenvolvedorService {
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    private DesenvolvedorRepository desenvolvedorRepository;

    // Função para validar um cliente
    public void validarDev(Desenvolvedor dev) throws ValidacaoException {
        if (dev.getNome() == null || dev.getNome().isBlank()) {
            throw new ValidacaoException("Nome inválido");
        }
        if (dev.getEmail() == null || dev.getEmail().isBlank()) {
            throw new ValidacaoException("Email inválido");
        }
    }

    public Desenvolvedor cadastrarDev(Desenvolvedor dev) throws ValidacaoException {
        validarDev(dev);
        return desenvolvedorRepository.save(dev);
    }

    public Desenvolvedor atualizarCliente(Desenvolvedor dev) throws ValidacaoException {
        // existe o dev com o id informado?
        if (dev.getId() == null) {
            throw new ValidacaoException("ID é nulo");
        }
        Optional<Desenvolvedor> devOptional = desenvolvedorRepository.findById(dev.getId());
        if (devOptional.isEmpty()) {
            throw new ValidacaoException("O dev " + dev.getId() + " não foi encontrado");
        }
        return desenvolvedorRepository.save(dev);
    }

    public Desenvolvedor buscarDesenvolvedorPorId(Long id) throws ValidacaoException {
        if (id == null || id == 0) {
            throw new ValidacaoException("O id é invalido");
        }
        Optional<Desenvolvedor> devOptional = desenvolvedorRepository.findById(id);
        if (devOptional.isEmpty()) {
            throw new ValidacaoException("O dev " + id + " não foi encontrado");
        }
        Desenvolvedor desenvolvedor = devOptional.get();
        return desenvolvedor;
    }

    // Delete a client by ID
    public void deleteCliente(Long id) throws ValidacaoException {
        try {
            // Check if the client exists before attempting to delete
            if (!desenvolvedorRepository.existsById(id)) {
                throw new ValidacaoException("Cliente not found with id: " + id);
            }
            if (id == null || id == 0) {
                throw new ValidacaoException("O id é invalido");
            }
            // Delete the client by its ID
            desenvolvedorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where entity does not exist
            throw new ValidacaoException("Error: Cliente not found with id " + id);
        }
    }

    public List<Venda> getVendasByDesenvolvedorId(Long desenvolvedorId) {
        return vendaRepository.findByDesenvolvedorId(desenvolvedorId);
    }

}
