package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.model.Produto;
import com.AjudaAqui.trabalhoFinalDAW.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public Page<Produto> listarProdutos(String termoBusca, Pageable pageable) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return produtoRepository.findByNomeContainingIgnoreCase(termoBusca.trim(), pageable);
        }
        return produtoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluirPorId(Long id) {
        produtoRepository.deleteById(id);
    }
}