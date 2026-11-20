package com.AjudaAqui.trabalhoFinalDAW.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Identificador único do cliente + auto incremento
    private Long id;

    @NotBlank(message = "O cliente deve possuir um nome!")
    @Size (min = 2, max = 100, message = "O nome deve conter entre 2 a 100 caracteres!")
    @Column(nullable = false, length = 100)
    // Nome do cliente, não pode ser nulo e tem tamanho máximo de 100 caracteres
    private String nome;

    @NotBlank(message = "O cliente deve possuir um CPF!")
    @Size (min = 11,max = 14, message = "CPF deve possuir 14 caracteres no total!")
    @CPF(message = "CPF inválido!")
    @Column(nullable = false, unique = true, length = 14)
    // CPF do cliente, não pode ser nulo, deve ser único e tem tamanho máximo de 14 caracteres
    private String cpf;

    @OneToMany(mappedBy = "clienteComprador", cascade = CascadeType.ALL, orphanRemoval = true)
    // Lista de pedidos do cliente, com cascade ALL e orphanRemoval true
    private List<Pedido> listaPedidosCliente = new ArrayList<>();
}