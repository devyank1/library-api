package com.estudo.api_livraria.service;

import com.estudo.api_livraria.model.Cliente;
import com.estudo.api_livraria.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado){
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }
}
