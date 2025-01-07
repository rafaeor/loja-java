package com.loja.demo.layers.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loja.demo.layers.entities.Cliente;
import com.loja.demo.layers.entities.Compra;

@Repository
public interface VendaRepository extends JpaRepository<Compra, Long> {

    // Encontra todas as compras de um cliente específico
    List<Compra> findByCliente(Cliente cliente);

    // Encontra todas as compras feitas em uma data específica
    List<Compra> findByData(Date data);

    // Encontra compras de um cliente em um intervalo de datas
    List<Compra> findByClienteAndDataBetween(Cliente cliente, Date startDate, Date endDate);

    @Query(value = "SELECT produto.nome AS produto, compra.data AS data_compra " +
            "FROM cliente " +
            "JOIN compra ON cliente.id = compra.cliente_id " +
            "JOIN compra_produto ON compra.id = compra_produto.compra_id " +
            "JOIN produto ON compra_produto.produto_id = produto.id " +
            "WHERE cliente.nome = :nome", nativeQuery = true)
    List<Object[]> findProdutosAndDataCompraByClienteNome(@Param("nome") String nome);

    // @Query(value="SELECT ** FROM compra")
    // List<Object[]> findAllCompras();
}
