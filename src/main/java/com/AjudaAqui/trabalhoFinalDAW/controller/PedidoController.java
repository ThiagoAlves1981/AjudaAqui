package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.model.Cliente;
import com.AjudaAqui.trabalhoFinalDAW.model.Pedido;
import com.AjudaAqui.trabalhoFinalDAW.model.StatusPedido;
import com.AjudaAqui.trabalhoFinalDAW.service.ClienteService;
import com.AjudaAqui.trabalhoFinalDAW.service.PedidoService;
import com.AjudaAqui.trabalhoFinalDAW.service.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService pedidoService, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    @GetMapping
public String listar(@RequestParam(required = false) Long clienteId,
                     @RequestParam(required = false) String busca,
                     @RequestParam(defaultValue = "0") int pagina,
                     Model model) {
    Pageable pageable = PageRequest.of(pagina, 10);
    Page<Pedido> pedidos = pedidoService.listarPedidos(clienteId, busca, pageable);

    model.addAttribute("pedidos", pedidos);
    model.addAttribute("clienteIdSeleccionado", clienteId);
    model.addAttribute("busca", busca);
    // Envia a lista de clientes para o filtro <select>
    model.addAttribute("clientes", clienteService.listarClientes("", PageRequest.of(0, 100)).getContent());

    return "pedidos/index";
}

   @GetMapping("/{id}")
public String detalhar(@PathVariable Long id, Model model) {
    Pedido pedido = pedidoService.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));
    
    model.addAttribute("pedido", pedido);
    model.addAttribute("statusOptions", StatusPedido.values());
    // Garante que os produtos cadastrados estejam disponíveis no <select> da tela
    model.addAttribute("produtos", produtoService.listarProdutos("", PageRequest.of(0, 100)).getContent());
    return "pedidos/detalhes";
}

    @GetMapping("/novo")
    public String novoForm(Model model, Pageable pageable) {
        model.addAttribute("pedido", new Pedido());
        // Traz os clientes para alimentar o <select> no formulário
        Page<Cliente> clientes = clienteService.listarClientes("", PageRequest.of(0, 100));
        model.addAttribute("clientes", clientes.getContent());
        return "pedidos/form";
    }

    @PostMapping("/salvar")
public String salvar(@ModelAttribute("pedido") Pedido pedido,
                     BindingResult result,
                     Model model) {

    // 1. Valida se um cliente foi realmente selecionado na tela
    if (pedido.getClienteComprador() == null || pedido.getClienteComprador().getId() == null) {
        result.rejectValue("clienteComprador.id", "error.pedido", "Você precisa selecionar um cliente para iniciar o pedido!");
    }

    // 2. Se houver erro (cliente não selecionado), recarrega o formulário com a lista de clientes
    if (result.hasErrors()) {
        Page<Cliente> clientes = clienteService.listarClientes("", PageRequest.of(0, 100));
        model.addAttribute("clientes", clientes.getContent());
        return "pedidos/form";
    }

    // 3. Cria o pedido (o Service define a data/hora e o status NOVO automaticamente)
    Pedido pedidoSalvo = pedidoService.criarNovoPedido(pedido);

    // 4. Redireciona para a tela de detalhes onde os itens serão adicionados
    return "redirect:/pedidos/" + pedidoSalvo.getId();
}

    @PostMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Long id,
            @RequestParam("statusPedido") StatusPedido novoStatus) {
        pedidoService.atualizarStatus(id, novoStatus);
        return "redirect:/pedidos/" + id;
    }
}