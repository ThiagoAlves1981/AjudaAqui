package com.AjudaAqui.trabalhoFinalDAW.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public Page<Item> listarItens(String termoBusca, Pageable pageable) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return itemRepository.findByProdutoNomeContainingIgnoreCase(termoBusca.trim(), pageable);
        }
        return itemRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public Item criarNovoItem(int qtd, Produto produto, Pedido pedido) {
        Item itemNovo = new Item();
        if(qtd < 1)
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a um");
        
        if(produto == null || produto.getPrecoAtual() == null)
            throw new IllegalArgumentException("Produto não existe");
        // Regra de Negócio 1: Quantidade deve ser de no mínimo 1 item

        itemNovo.setQuantidade(qtd);

        // Regra de Negócio 2: Todo item deve possuir um preço
        itemNovo.setPrecoUnitario(produto.getPrecoAtual());

        // Regra de Negócio 3: Todo item deve estar ligado a um pedido
        itemNovo.setPedido(pedido);

        //Regra de Negócio 4: Todo item deve estar ligado a um produto
        itemNovo.setProduto(produto);

        return itemRepository.save(itemNovo);
    }

    @Transactional
    public void editarQtdItem(Long itemIdLong, int novaQtd) {
        Item itemEditado = itemRepository.findById(itemIdLong).orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        
        if(novaQtd < 1)
            throw new IllegalArgumentException("Nova quantidade não pode ser menor que 0. Para excluir o item, clique em Excluir");
        itemEditado.setQuantidade(novaQtd);
        itemRepository.save(itemEditado);
    }

    @Transactional 
    public void excluirItem(Long itemIdLong){
        Item item = itemRepository.findById(itemIdLong).orElseThrow(()->new IllegalArgumentException("Item não encontrado"));

        itemRepository.delete(item);
    }

    @Transactional (readOnly = true)
    public BigDecimal calcularSubTotal(Long itemIdLong){
        Item item = itemRepository.findById(itemIdLong).orElseThrow(()-> new IllegalArgumentException("Item não encontrado"));
        return item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
    }
}