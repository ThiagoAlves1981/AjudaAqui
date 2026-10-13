package com.AjudaAqui.trabalhoFinalDAW.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
@Data 
public class Pedido {
    private Long id;
    private LocalDateTime dataHoraPedido;
    private StatusPedido statusPedido;
    private Cliente clienteComprador;
    private List<Item> listaItensPedido;
}
