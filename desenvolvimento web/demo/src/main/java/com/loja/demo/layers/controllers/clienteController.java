package com.loja.demo.layers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.entities.Compra;
import com.loja.demo.layers.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente newCliente = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(newCliente, HttpStatus.OK); // 201 Created status
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Retorna mensagem de erro, porem
            // pode apresentar os logs de erro do mysql também, usar com cuidado
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.atualizarCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        } catch (ValidacaoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok().build(); // Return HTTP 200 if successful
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build(); // Return HTTP 404 if not found
        }
    }

    // Endpoint to add a compra to a client
    @PostMapping("/{clienteId}/template")
    public ResponseEntity<String> addTemplate(@PathVariable Long clienteId, @RequestParam String produto)
            throws ValidacaoException {
        clienteService.addCompraToCliente(clienteId, produto, "template");
        return ResponseEntity.status(HttpStatus.OK).body("Você adquiriu o template " + produto + " com sucesso!");
    }

    // Endpoint to add a compra to a client
    @PostMapping("/{clienteId}/contrato")
    public ResponseEntity<String> addContrato(@PathVariable Long clienteId, @RequestParam String produto)
            throws ValidacaoException {
        clienteService.addCompraToCliente(clienteId, produto, "contrato");
        return ResponseEntity.status(HttpStatus.OK).body("Você adquiriu o contrato " + produto + " com sucesso!");
    }

    // Endpoint to show templates for a given client
    @GetMapping("/{clienteId}/templates")
    public ResponseEntity<List<Compra>> getTemplates(@PathVariable Long clienteId) {
        List<Compra> templates = clienteService.getTemplatesByClienteId(clienteId);
        return ResponseEntity.status(HttpStatus.OK).body(templates);
    }

    // Endpoint to show contracts for a given client
    @GetMapping("/{clienteId}/contratos")
    public ResponseEntity<List<Compra>> getContratos(@PathVariable Long clienteId) {
        List<Compra> contratos = clienteService.getContratosByClienteId(clienteId);
        return ResponseEntity.status(HttpStatus.OK).body(contratos);
    }

}
