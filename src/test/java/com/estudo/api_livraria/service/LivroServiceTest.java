package com.estudo.api_livraria.service;

import com.estudo.api_livraria.model.Livro;
import com.estudo.api_livraria.repository.LivroRepository;
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


@ExtendWith(MockitoExtension.class)//habilita o uso do Mockito no JUnit 5.
public class LivroServiceTest {

    @Mock // Cria um mock do LivroRepository
    private LivroRepository livroRepository;

    @InjectMocks // Injeta o mock no LivroService
    private LivroService livroService;

    private Livro livro1;
    private Livro livro2;

    @BeforeEach
    void setUp() {
        // Cria instâncias de livros para serem usadas nos testes
        livro1 = new Livro(1L, "Livro A", "Autor A", 29.99);
        livro2 = new Livro(2L, "Livro B", "Autor B", 39.99);
    }

    @Test
    void testListarTodos() {
        when(livroRepository.findAll()).thenReturn(Arrays.asList(livro1, livro2));

        List<Livro> livros = livroService.listarTodos();
        assertEquals(2, livros.size());
        assertEquals("Livro A", livros.get(0).getTitulo());
        assertEquals("Livro B", livros.get(1).getTitulo());
    }

    @Test
    void testSalvarLivro() {
        when(livroRepository.save(livro1)).thenReturn(livro1);

        Livro livroSalvo = livroService.salvar(livro1);
        assertNotNull(livroSalvo);
        assertEquals("Livro A", livroSalvo.getTitulo());
    }

    @Test
    void testAtualizarLivro() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro1));
        when(livroRepository.save(livro1)).thenReturn(livro1);

        Livro livroAtualizado = new Livro(1L, "Livro A Atualizado", "Autor A atualizado", 35.99);
        Livro livro = livroService.atualizar(1L, livroAtualizado);
        assertEquals("Livro A Atualizado", livro.getTitulo());
        assertEquals(35.99, livro.getPreco());
    }

    @Test
    void testDeletarLivro() {
        livroService.deletar(1L);
        verify(livroRepository, times(1)).deleteById(1L);
    }

    @Test
    void testLivroNaoEncontrado() {
        when(livroRepository.findById(3L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            livroService.atualizar(3L, livro1);
        });

        assertEquals("Livro não encontrado!", exception.getMessage());
    }
    //TESTADO
}
