package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;
import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import com.AjudaAqui.trabalhoFinalDAW.model.Produto;
import com.AjudaAqui.trabalhoFinalDAW.service.ItemService;
import com.AjudaAqui.trabalhoFinalDAW.service.PedidoService;
import com.AjudaAqui.trabalhoFinalDAW.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;
    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    public ItemController(ItemService itemService, PedidoService pedidoService, ProdutoService produtoService) {
        this.itemService = itemService;
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
    }

    @PostMapping("/adicionar")
    public String adicionarItem(@RequestParam("pedidoId") Long pedidoId,
                                @RequestParam("produtoId") Long produtoId,
                                @RequestParam("quantidade") int quantidade,
                                Model model) {

        Pedido pedido = pedidoService.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        Produto produto = produtoService.buscarPorId(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        itemService.criarNovoItem(quantidade, produto, pedido);

        // Recarrega o pedido atualizado com os seus novos itens e subjacentes
        Pedido pedidoAtualizado = pedidoService.buscarPorId(pedidoId).get();
        model.addAttribute("pedido", pedidoAtualizado);

        // Retorna apenas o fragmento HTML contendo a lista atualizada de itens do pedido (para o HTMX)
        return "pedidos/detalhes :: tabela-itens";
    }

    @PostMapping("/editar-quantidade")
    public String editarQuantidade(@RequestParam("itemId") Long itemId,
                                   @RequestParam("quantidade") int novaQuantidade,
                                   @RequestParam("pedidoId") Long pedidoId,
                                   Model model) {

        itemService.editarQtdItem(itemId, novaQuantidade);

        Pedido pedidoAtualizado = pedidoService.buscarPorId(pedidoId).get();
        model.addAttribute("pedido", pedidoAtualizado);

        return "pedidos/detalhes :: tabela-itens";
    }

    @DeleteMapping("/deletar/{id}")
    public String removerItem(@PathVariable Long id,
                              @RequestParam("pedidoId") Long pedidoId,
                              Model model) {

        itemService.excluirItem(id);

        Pedido pedidoAtualizado = pedidoService.buscarPorId(pedidoId).get();
        model.addAttribute("pedido", pedidoAtualizado);

        return "pedidos/detalhes :: tabela-itens";
    }
}