package com.estudo.api_livraria.controller;

import com.estudo.api_livraria.model.Livro;
import com.estudo.api_livraria.repository.LivroRepository;
import com.estudo.api_livraria.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Operation(summary = "Lista todos os livros", description = "Lista todos os livros existentes no sistema por meio de uma list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro listado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado!")
    })
    @GetMapping
    public List<Livro> listarTodosLivros(){
        return livroService.listarTodos();
    }

    @Operation(summary = "Salva o livro", description = "Salva o livro no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos!")
    })
    @PostMapping
    public Livro salvarLivros(@RequestBody Livro livro){
        return livroService.salvar(livro);
    }

    @Operation(summary = "Atualiza o livro", description = "Atualiza o livro por outro já existente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Livro atualizado com sucesso e os dados atualizados são retornados!"),
            @ApiResponse(responseCode = "204", description = "Livro atualizado com sucesso e sem conteúdo no corpo da resposta!"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados incorretos!"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivros(@PathVariable Long id, @RequestBody Livro livro){
        return ResponseEntity.ok(livroService.atualizar(id, livro));
    }

    @Operation(summary = "Deleta o livro", description = "Deleta o livro existente na lista criada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivros(@PathVariable Long id){
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
