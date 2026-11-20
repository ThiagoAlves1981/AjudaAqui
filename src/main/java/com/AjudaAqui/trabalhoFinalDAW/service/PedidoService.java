package com.AjudaAqui.trabalhoFinalDAW.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;
import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import com.AjudaAqui.trabalhoFinalDAW.model.Produto;
import com.AjudaAqui.trabalhoFinalDAW.model.StatusPedido;
import com.AjudaAqui.trabalhoFinalDAW.repository.ItemRepository;
import com.AjudaAqui.trabalhoFinalDAW.repository.PedidoRepository;
import com.AjudaAqui.trabalhoFinalDAW.repository.ProdutoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemRepository itemRepository,ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
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

    @Transactional
public Pedido finalizarPedido(Long pedidoId) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new IllegalArgumentException("Pedido inválido."));

    if (pedido.getStatusPedido() != StatusPedido.EM_ANDAMENTO) {
        throw new IllegalStateException("Apenas pedidos pendentes podem ser finalizados.");
    }

    List<Item> itens = itemRepository.findByPedidoId(pedidoId);
    if (itens.isEmpty()) {
        throw new IllegalStateException("Não é possível finalizar um pedido sem itens.");
    }

    // Baixa de estoque e cálculo de total garantido
    BigDecimal totalCalculado = BigDecimal.ZERO;
    
    for (Item item : itens) {
        Produto produto = item.getProduto();
        
        if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
            throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getNome() + " | Quantidade atual: " + produto.getQuantidadeEstoque());
        }
        
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
        produtoRepository.save(produto); // Atualiza o estoque do produto
        
        BigDecimal subtotal = item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()));
        totalCalculado = totalCalculado.add(subtotal);
    }

    pedido.setValorTotal(totalCalculado);
    pedido.setStatusPedido(StatusPedido.FINALIZADO); 
    
    return pedidoRepository.save(pedido);
}

@Transactional
public void atualizarTotal(Long pedidoId) {
    Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
    BigDecimal total = itemRepository.somarTotalDoPedido(pedidoId);
    pedido.setValorTotal(total != null ? total : BigDecimal.ZERO);
    pedidoRepository.save(pedido);
}
}