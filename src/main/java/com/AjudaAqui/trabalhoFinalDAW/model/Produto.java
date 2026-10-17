package com.AjudaAqui.trabalhoFinalDAW.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Identificador único do produto + auto incremento
    private Long id;

    @Column(nullable = false, length = 100)
    // Nome do produto, não pode ser nulo e tem tamanho máximo de 100 caracteres
    private String nome;

    @Column(length = 255)
    // Descrição do produto, pode ser nula e tem tamanho máximo de 255 caracteres
    private String descricao;

    @Column(name = "preco_atual", nullable = false, precision = 10, scale = 2)
    // Preço atual do produto, não pode ser nulo e tem precisão de 10 dígitos e escala de 2 e o nome da coluna no banco de dados é "preco_atual"
    private BigDecimal precoAtual;
}