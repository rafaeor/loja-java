package com.loja.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loja.demo.exceptions.ValidacaoException;
import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.entities.Compra;
import com.loja.demo.layers.entities.Contrato;
import com.loja.demo.layers.entities.Desenvolvedor;
import com.loja.demo.layers.entities.Template;
import com.loja.demo.layers.repositories.ClienteRepository;
import com.loja.demo.layers.services.ClienteService;
import com.loja.demo.layers.services.CompraService;
import com.loja.demo.layers.services.ContratoService;
import com.loja.demo.layers.services.DesenvolvedorService;
import com.loja.demo.layers.services.TemplateService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	public ClienteRepository clienteRepository;

	@Autowired
	public ClienteService clienteService;
	@Autowired
	public DesenvolvedorService desenvolvedorService;
	@Autowired
	public ContratoService contratoService;
	@Autowired
	public CompraService compraService;

	@Autowired
	public TemplateService templateService;

	@Test
	void cadastrarCliente() throws ValidacaoException {
		Cliente cliente = new Cliente();
		cliente.setNome("Caio");
		cliente.setCpf("129.651.739-10");
		cliente.setEmail("caio@algo.com");
		assertDoesNotThrow(() -> {
			clienteService.cadastrarCliente(cliente);
		});
	}

	@Test
	void cadastrarDesenvolvedor() throws ValidacaoException {
		Desenvolvedor desenvolvedor = new Desenvolvedor();
		desenvolvedor.setNome("Joao");
		desenvolvedor.setEmail("joao@algo.com");
		assertDoesNotThrow(() -> {
			desenvolvedorService.cadastrarDev(desenvolvedor);
		});
	}

	@Test
	void CadastrarTemplate() throws ValidacaoException {
		Template template = new Template();
		template.setNome("SiteDeApostas");
		template.setDescricao(
				"O seguinte sistema é um site de apostas online, onde o usuário pode apostar em diversos eventos esportivos. Possui front-end e back-end.");
		template.setPreco(40.0);
		template.setDesenvolvedor(desenvolvedorService.buscarDesenvolvedorPorId(1L));
		templateService.cadastrarTemplate(template);
	}

	@Test
	void CadastrarContrato() throws ValidacaoException {
		Contrato contrato = new Contrato();
		contrato.setNome("FazerSistemaCadastramento");
		contrato.setPreco(40.0);
		contrato.setDataInicio(new Date());
		contrato.setDesenvolvedor(desenvolvedorService.buscarDesenvolvedorPorId(1L));
		contratoService.cadastrarContrato(contrato);
	}

	@Test
	void testeCompraCliente() throws ValidacaoException {
		clienteService.addCompraToCliente(1L, "SiteDeApostas", "template");
	}

	@Test
	void mostrarComprasDeCliente() {

		List<Compra> compras = compraService.obterComprasPorClienteId(1l);
		compras.forEach(compra -> {
			System.out.println("Compra ID: " + compra.getId());
			System.out.println("Data: " + compra.getData());
			System.out.println("Template: " + compra.getTemplate().getNome());
		});

	}
	/*
	 * @Test
	 * void testeRemoverCliente() {
	 * clienteRepository.deleteById(3l);
	 * }
	 * 
	 * @Test
	 * void atualizarCliente() {
	 * Cliente cliente = clienteRepository.findById(1l).get();
	 * cliente.setNome("Zara");
	 * clienteRepository.save(cliente);
	 * }
	 * 
	 * @Test
	 * void testeGetClientePorNome() {
	 * List<Cliente> clientes = clienteRepository.buscarPorNome("%Pedro%");
	 * System.out.println(clientes);
	 * }
	 * 
	 * @Test
	 * void GetById() {
	 * Optional<Cliente> clienteid = clienteRepository.findById(2L);
	 * System.out.println(clienteid);
	 * }
	 * 
	 * @Test
	 * void deletarClienteporId() throws ValidacaoException {
	 * assertThrows(ValidacaoException.class, () -> {
	 * clienteService.deleteCliente(3L);
	 * ; // lança a exceção, ou seja nao cadastra cliente
	 * });
	 * }
	 */
}
