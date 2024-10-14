package com.estudo.api_livraria.repository;

import com.estudo.api_livraria.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}
