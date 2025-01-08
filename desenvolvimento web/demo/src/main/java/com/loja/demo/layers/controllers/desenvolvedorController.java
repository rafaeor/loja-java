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
import com.loja.demo.layers.entities.Desenvolvedor;
import com.loja.demo.layers.entities.Venda;
import com.loja.demo.layers.services.DesenvolvedorService;

@RestController
@RequestMapping("/dev")
public class desenvolvedorController {

    @Autowired
    private DesenvolvedorService desenvolvedorService;

    @PostMapping
    public ResponseEntity<Object> createDev(@RequestBody Desenvolvedor dev) {
        try {
            Desenvolvedor newCliente = desenvolvedorService.cadastrarDev(dev);
            return new ResponseEntity<>(newCliente, HttpStatus.OK); // 201 Created status
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Retorna mensagem de erro, porem
            // pode apresentar os logs de erro do mysql também, usar com cuidado
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizarCliente(@RequestBody Desenvolvedor cliente) {
        try {
            desenvolvedorService.atualizarCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        } catch (ValidacaoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        try {
            desenvolvedorService.deleteCliente(id);
            return ResponseEntity.ok().build(); // Return HTTP 200 if successful
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build(); // Return HTTP 404 if not found
        }
    }

    // Método GET para retornar todas as vendas relacionadas ao ID do desenvolvedor
    @GetMapping("/{desenvolvedorId}/vendas")
    public ResponseEntity<List<Venda>> getVendasByDesenvolvedorId(@PathVariable Long desenvolvedorId) {
        List<Venda> vendas = desenvolvedorService.getVendasByDesenvolvedorId(desenvolvedorId);
        return ResponseEntity.status(HttpStatus.OK).body(vendas);
    }

    // Endpoint to add a compra to a client
    @PostMapping("/{desenvolvedorId}/template")
    public ResponseEntity<String> addTemplate(@PathVariable Long id, @RequestParam String produto)
            throws ValidacaoException {
        desenvolvedorService.addCompra(id, produto, "template");
        return ResponseEntity.status(HttpStatus.OK).body("Você cadastrou o template " + produto + " com sucesso!");
    }

}
