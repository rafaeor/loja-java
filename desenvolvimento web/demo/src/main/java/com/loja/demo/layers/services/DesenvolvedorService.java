package com.loja.demo.layers.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.repositories.ClienteRepository;
import com.loja.demo.utils.CPFUtils;

@Service
public class DesenvolvedorService {
    // Função para validar um cliente
    public void validarCliente(Cliente cliente) throws ValidacaoException {
        // Validação do CPF
        if (!CPFUtils.isValidCPF(cliente.getCpf())) {
            throw new ValidacaoException("CPF Inválido");
        }
        // Validação do nome
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ValidacaoException("Nome inválido");
        }
    }

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) throws ValidacaoException {
        // Chama a função de validação antes de cadastrar
        validarCliente(cliente);
        // Se não houver exceção de validação, salva o cliente
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) throws ValidacaoException {
        // existe o cliente com o id informado?
        if (cliente.getId() == null) {
            throw new ValidacaoException("ID é nulo");
        }
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());
        if (clienteOptional.isEmpty()) {
            throw new ValidacaoException("O Cliente " + cliente.getId() + " não foi encontrado");
        }
        if (!CPFUtils.isValidCPF(cliente.getCpf())) {
            throw new ValidacaoException("CPF Inválido");
        }
        return clienteRepository.save(cliente);
    }

    // Delete a client by ID
    public void deleteCliente(Long id) throws ValidacaoException {
        try {
            // Check if the client exists before attempting to delete
            if (!clienteRepository.existsById(id)) {
                throw new ValidacaoException("Cliente not found with id: " + id);
            }
            if (id == null || id == 0) {
                throw new ValidacaoException("O id é invalido");
            }
            // Delete the client by its ID
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where entity does not exist
            throw new ValidacaoException("Error: Cliente not found with id " + id);
        }
    }

}
