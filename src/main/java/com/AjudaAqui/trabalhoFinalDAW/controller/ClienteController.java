package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.model.Cliente;
import com.AjudaAqui.trabalhoFinalDAW.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(
            @RequestParam(value = "busca", required = false, defaultValue = "") String busca,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "5") int tamanho,
            @RequestHeader(value = "HX-Request", required = false) boolean isHtmx,
            Model model) {

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());
        Page<Cliente> clientes = clienteService.listarClientes(busca, pageable);

        model.addAttribute("clientes", clientes);
        model.addAttribute("busca", busca);

        if (isHtmx) {
            return "clientes/tabela :: tabela-clientes";
        }

        return "clientes/index";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("cliente") Cliente cliente,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "clientes/form";
        }

        try {
            clienteService.salvar(cliente);
            return "redirect:/clientes";
        } catch (IllegalArgumentException e) {
            // Captura erro de CPF duplicado lançado pelo ClienteService
            result.rejectValue("cpf", "error.cliente", e.getMessage());
            return "clientes/form";
        }
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id,
                          @RequestParam(value = "busca", required = false, defaultValue = "") String busca,
                          @RequestParam(value = "pagina", defaultValue = "0") int pagina,
                          @RequestParam(value = "tamanho", defaultValue = "5") int tamanho,
                          Model model) {
        clienteService.excluirPorId(id);

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());
        Page<Cliente> clientes = clienteService.listarClientes(busca, pageable);

        model.addAttribute("clientes", clientes);
        model.addAttribute("busca", busca);

        return "clientes/tabela :: tabela-clientes";
    }
}