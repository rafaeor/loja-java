package com.loja.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.repositories.ClienteRepository;
import com.loja.demo.layers.services.ClienteService;
import com.loja.demo.layers.services.TemplateService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	public ClienteRepository clienteRepository;

	@Autowired
	public ClienteService clienteService;
	@Autowired
	public TemplateService produtoService;

	@Test
	void testeInserirCliente() {
		/*
		 * for(int i =0; i < 9; i++){
		 * Cliente cliente = new Cliente();
		 * cliente.setNome("Dom Pedro "+i+"o");
		 * cliente.setCpf("123.456.789-0"+i);
		 * cliente.setDataNascimento(new Date());
		 * clienteRepository.save(cliente);
		 * }
		 */
		Cliente cliente = new Cliente();
		cliente.setNome("Maria Asda");
		cliente.setCpf("123.651.739-0");
		clienteRepository.save(cliente);
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Douglas ZXCzxc");
		cliente2.setCpf("083.456.739-0");
		clienteRepository.save(cliente2);
	}

	@Test
	void testeRemoverCliente() {
		clienteRepository.deleteById(3l);
	}

	@Test
	void atualizarCliente() {
		Cliente cliente = clienteRepository.findById(2l).get();
		cliente.setNome("Zara");
		clienteRepository.save(cliente);
	}

	@Test
	void testeGetClientePorNome() {
		List<Cliente> clientes = clienteRepository.buscarPorNome("%Pedro%");
		System.out.println(clientes);
	}

	@Test
	void GetById() {
		Optional<Cliente> clienteid = clienteRepository.findById(2L);
		System.out.println(clienteid);
	}

	@Test
	void testcadastrarClienteValidacao() throws ValidacaoException {
		Cliente cliente = new Cliente();
		cliente.setNome("Douglas Axxxx");
		cliente.setCpf("801.917.960-77");
		cliente.setDataNascimento(new Date());
		assertThrows(ValidacaoException.class, () -> {
			clienteService.cadastrarCliente(cliente); // lança a exceção, ou seja nao cadastra cliente
		});
	}

	@Test
	void TestCadastrarProduto() throws ValidacaoException {
		Template produto = new Template();
		produto.setNome("Aposta100k");
		produto.setDescricao("O usuario botou 100 mil dolares");
		produtoService.cadastrarProduto(produto);
	}

	@Test
	void deletarClienteporId() throws ValidacaoException {
		assertThrows(ValidacaoException.class, () -> {
			clienteService.deleteCliente(3L);
			; // lança a exceção, ou seja nao cadastra cliente
		});
	}
}
