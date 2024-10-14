package com.estudo.api_livraria.repository;

import com.estudo.api_livraria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {}
