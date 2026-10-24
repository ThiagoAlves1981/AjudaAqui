package com.AjudaAqui.trabalhoFinalDAW.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Item;

@Repository 
public interface ItemRepository extends JpaRepository<Item, Long>{
    Page<Item> findByProdutoNomeContainingIgnoreCase(String nome, Pageable pageable);
}
