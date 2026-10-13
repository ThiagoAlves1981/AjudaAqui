package com.AjudaAqui.trabalhoFinalDAW.model;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data  
public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private List<Pedido> listaPedidosCliente;

}
