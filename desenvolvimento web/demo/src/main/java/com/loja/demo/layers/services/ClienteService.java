package com.loja.demo.layers.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.entities.Compra;
import com.loja.demo.layers.entities.Contrato;
import com.loja.demo.layers.entities.Desenvolvedor;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.entities.Venda;
import com.loja.demo.layers.repositories.ClienteRepository;
import com.loja.demo.layers.repositories.CompraRepository;
import com.loja.demo.layers.repositories.TemplateRepository;
import com.loja.demo.layers.repositories.VendaRepository;
import com.loja.demo.layers.repositories.ContratoRepository;

@Service
public class ClienteService {
    // Função para validar um cliente
    public void validarCliente(Cliente cliente) throws ValidacaoException {
        // Validação do CPF
        /*
         * if (!CPFUtils.isValidCPF(cliente.getCpf())) {
         * throw new ValidacaoException("CPF Inválido");
         * }
         */

        // Validação do nome
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ValidacaoException("Nome inválido");
        }

        // Validação do Email
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new ValidacaoException("Email inválido");
        }
    }

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    ContratoRepository contratoRepository;

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
        /*
         * if (!CPFUtils.isValidCPF(cliente.getCpf())) {
         * throw new ValidacaoException("CPF Inválido");
         * }
         */
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

    public List<Compra> getTemplatesByClienteId(Long clienteId) {
        return compraRepository.findTemplatesByClienteId(clienteId);
    }

    public List<Compra> getContratosByClienteId(Long clienteId) {
        return compraRepository.findContratosByClienteId(clienteId);
    }

    public void addCompraToCliente(Long clienteId, String nomeProduto, String tipo) throws ValidacaoException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new ValidacaoException("Cliente not found with id: " + clienteId);
        }
        Cliente cliente = clienteOptional.get();
        if (tipo != "template" && tipo != "contrato") {
            throw new ValidacaoException("Tipo de produto inválido: " + tipo);
        }
        if (tipo == "template") {
            Optional<Template> templateOptional = templateRepository.getTemplatePorNome(nomeProduto);
            if (clienteOptional.isEmpty()) {
                throw new ValidacaoException("Template not found: " + nomeProduto);
            }
            Template template = templateOptional.get();
            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setData(new Date());
            compra.setTemplate(template);
            compraRepository.save(compra);
        } else if (tipo == "contrato") {
            Optional<Contrato> contratoOpicional = contratoRepository.getPorNome(nomeProduto);
            if (clienteOptional.isEmpty()) {
                throw new ValidacaoException("Contrato not found: " + nomeProduto);
            }
            Contrato template = contratoOpicional.get();
            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setData(new Date());
            compra.setContrato(template);
            compraRepository.save(compra);

            // Registro da venda para o vendedor
            Optional<Desenvolvedor> devOptional = template.getDesenvolvedor();
            if (devOptional.isEmpty()) {
                throw new ValidacaoException("Dev not found: " + nomeProduto);
            }
            Desenvolvedor desenvolvedor = devOptional.get();
            Venda venda = new Venda();
            venda.setDesenvolvedor(desenvolvedor);
            venda.setData(new Date());
            venda.setPreco(template.getPreco());
            venda.setTipo(tipo);
            vendaRepository.save(venda);
        }
    }
}
