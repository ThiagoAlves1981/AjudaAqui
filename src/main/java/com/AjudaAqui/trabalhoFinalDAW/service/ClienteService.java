package com.AjudaAqui.trabalhoFinalDAW.service;

import com.AjudaAqui.trabalhoFinalDAW.model.Cliente;
import com.AjudaAqui.trabalhoFinalDAW.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public Page<Cliente> listarClientes(String termoBusca, Pageable pageable) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            String termo = termoBusca.trim();
            return clienteRepository.findByNomeContainingIgnoreCaseOrCpfContaining(termo, termo, pageable);
        }
        return clienteRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        // Regra de Negócio 1: Verificar se o CPF já pertence a OUTRO cliente
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        
        if (clienteExistente.isPresent()) {
            // Se for uma EDIÇÃO, o ID do cliente retornado do banco será IGUAL ao cliente que estamos salvando.
            // Se for um NOVO CADASTRO ou CPF de OUTRA pessoa, os IDs serão DIFERENTES.
            boolean mesmoCliente = cliente.getId() != null && cliente.getId().equals(clienteExistente.get().getId());
            
            if (!mesmoCliente) {
                throw new IllegalArgumentException("Já existe um cliente registado com este CPF.");
            }
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluirPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        // Regra de Negócio 2: Impedir exclusão se o cliente tiver pedidos associados
        if (cliente.getListaPedidosCliente() != null && !cliente.getListaPedidosCliente().isEmpty()) {
            throw new IllegalArgumentException("Não é possível excluir um cliente que possui pedidos registados.");
        }

        clienteRepository.delete(cliente);
    }
}