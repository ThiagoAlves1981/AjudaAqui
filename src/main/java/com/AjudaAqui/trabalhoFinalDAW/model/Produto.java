package com.AjudaAqui.trabalhoFinalDAW.model;
import java.math.BigDecimal;
import lombok.Data;

@Data 
public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal precoAtual;
}
