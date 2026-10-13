package com.AjudaAqui.trabalhoFinalDAW.model;

import java.math.BigDecimal;

import lombok.Data;
@Data 
public class Item {
    private Long id;
    private int quantidade;
    private BigDecimal precoUnitario;
    private Produto produto;

}
