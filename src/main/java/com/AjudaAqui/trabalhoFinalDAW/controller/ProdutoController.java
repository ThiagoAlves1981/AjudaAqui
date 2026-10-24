package com.AjudaAqui.trabalhoFinalDAW.controller;

import com.AjudaAqui.trabalhoFinalDAW.model.Produto;
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
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public String listar(
            @RequestParam(value = "busca", required = false, defaultValue = "") String busca,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "5") int tamanho,
            @RequestHeader(value = "HX-Request", required = false) boolean isHtmx,
            Model model) {

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("nome").ascending());
        Page<Produto> produtos = produtoService.listarProdutos(busca, pageable);

        model.addAttribute("produtos", produtos);
        model.addAttribute("busca", busca);

        if (isHtmx) {
            return "produtos/tabela :: tabela-produtos";
        }

        return "produtos/index";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido com ID: " + id));
        model.addAttribute("produto", produto);
        return "produtos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "produtos/form";
        }
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id,
                          @RequestParam(value = "busca", required = false, defaultValue = "") String busca,
                          @RequestParam(value = "pagina", defaultValue = "0") int pagina,
                          Model model) {
        produtoService.excluirPorId(id);

        Pageable pageable = PageRequest.of(pagina, 5, Sort.by("nome").ascending());
        Page<Produto> produtos = produtoService.listarProdutos(busca, pageable);

        model.addAttribute("produtos", produtos);
        model.addAttribute("busca", busca);

        return "produtos/tabela :: tabela-produtos";
    }
}