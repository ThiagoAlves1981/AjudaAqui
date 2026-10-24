package com.AjudaAqui.trabalhoFinalDAW.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;
import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import com.AjudaAqui.trabalhoFinalDAW.model.StatusPedido;
import com.AjudaAqui.trabalhoFinalDAW.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

   @Transactional(readOnly = true)
public Page<Pedido> listarPedidos(Long clienteId, String termoBusca, Pageable pageable) {
    if (clienteId != null) {
     return pedidoRepository.findByClienteCompradorId(clienteId, pageable);
    }
    if (termoBusca != null && !termoBusca.isBlank()) {
        return pedidoRepository.findByClienteCompradorNomeContainingIgnoreCase(termoBusca.trim(), pageable);
    }
    return pedidoRepository.findAll(pageable);
}
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido criarNovoPedido(Pedido pedido) {
        // Regra de Negócio 1: Definir a data/hora exata da criação
        pedido.setDataHoraPedido(LocalDateTime.now());

        // Regra de Negócio 2: Todo pedido começa com o status "NOVO"
        pedido.setStatusPedido(StatusPedido.NOVO);

        // Regra de Negócio 3: Garantir o vínculo de 1:N entre o Pedido e cada Item da
        // lista
        if (pedido.getListaItensPedido() != null) {
            for (Item item : pedido.getListaItensPedido()) {
                item.setPedido(pedido);
            }
        }

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedido.setStatusPedido(novoStatus);
        pedidoRepository.save(pedido);
    }
}