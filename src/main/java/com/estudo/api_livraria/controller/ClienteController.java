package com.estudo.api_livraria.controller;

import com.estudo.api_livraria.model.Cliente;
import com.estudo.api_livraria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Listar todos os clientes", description = "Lista todos os clientes cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente listado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado!")
    })
    @GetMapping
    public List<Cliente> listarTodosClientes(){
        return clienteService.listarTodos();
    }

    @Operation(summary = "Salva o cliente", description = "Salva o cliente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos!")
    })
    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente existente na lista pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso e os dados atualizados são retornados!"),
            @ApiResponse(responseCode = "204", description = "Cliente atualizado com sucesso e sem conteúdo no corpo da resposta!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados incorretos!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }

    @Operation(summary = "Deleta um cliente", description = "Deleta um cliente da lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
