package com.estudo.api_livraria.service;

import com.estudo.api_livraria.model.Cliente;
import com.estudo.api_livraria.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp(){
        cliente1 = new Cliente(1L, "Cliente A", "yankzin@gmail.com");
        cliente2 = new Cliente(2L, "Cliente B", "raimundo.peres@gmail.com");
    }

    @Test
    void testListarClientes(){
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.listarTodos();
        assertEquals(2, clientes.size()); //espera o tamanho (size) da lista (2).
        assertEquals("Cliente A", clientes.get(0).getNome()); //Verifica onde esta posto na lista (0)
        assertEquals("Cliente B", clientes.get(1).getNome());
    }

    @Test
    void testSalvarClientes(){
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente clienteSalvo = clienteService.salvar(cliente1);
        assertNotNull(clienteSalvo);
        assertEquals("Cliente A", clienteSalvo.getNome());
    }

    @Test
    void testAtualizarCliente(){
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente clienteAtualizado = new Cliente(1L, "Cliente A atualizado", "email@atualizado.com");
        Cliente cliente = clienteService.atualizar(1L, clienteAtualizado);
        assertEquals("Cliente A atualizado", cliente.getNome());
        assertEquals("email@atualizado.com", cliente.getEmail());
    }

    @Test
    void testDeletarCliente(){
        clienteService.deletar(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testClienteNaoEncontrado(){
        when(clienteRepository.findById(3L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.atualizar(3L, cliente1);
        });

        assertEquals("Cliente não encontrado!", exception.getMessage());
    }
    //TESTADO
}
