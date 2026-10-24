package com.AjudaAqui.trabalhoFinalDAW.repository;

import com.AjudaAqui.trabalhoFinalDAW.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Procura por CPF exato (útil para validar duplicados)
    Optional<Cliente> findByCpf(String cpf);

    // Procura paginada por Nome OU por CPF contendo o termo pesquisado
    Page<Cliente> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf, Pageable pageable);
}