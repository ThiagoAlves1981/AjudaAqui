package com.AjudaAqui.trabalhoFinalDAW.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByProdutoNomeContainingIgnoreCase(String nome, Pageable pageable);

    List<Item> findByPedidoId(Long id);

    @Query("SELECT SUM(i.quantidade * i.precoUnitario) FROM Item i WHERE i.pedido.id = :pedidoId")
    BigDecimal somarTotalDoPedido(@Param("pedidoId") Long pedidoId);
}
