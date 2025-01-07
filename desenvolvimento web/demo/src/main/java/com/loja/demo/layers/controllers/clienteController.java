package com.loja.demo.layers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.services.ClienteService;

@RestController // why rest? Does not save the connection opened after the request.
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente newCliente = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(newCliente,HttpStatus.OK);  // 201 Created status
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

}
