package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Busca pedidos filtrando pelo ID do cliente
    Page<Pedido> findByClienteCompradorId(Long clienteId, Pageable pageable);

    // Busca pedidos pelo nome do cliente (para o campo de pesquisa)
    Page<Pedido> findByClienteCompradorNomeContainingIgnoreCase(String nome, Pageable pageable);
}