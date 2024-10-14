package com.estudo.api_livraria.service;

import com.estudo.api_livraria.model.Livro;
import com.estudo.api_livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // listar todos, salvar, atualizar e deletar

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public Livro atualizar(Long id, Livro livroAtualizado){
        return livroRepository.findById(id).map(livro -> {
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setPreco(livroAtualizado.getPreco());
            return livroRepository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

    public void deletar(Long id){
        livroRepository.deleteById(id);
    }
}
