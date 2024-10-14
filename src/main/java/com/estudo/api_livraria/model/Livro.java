package com.estudo.api_livraria.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 70)
    private String titulo;
    @Column(nullable = false, unique = true, length = 120)
    private String autor;
    @Column(nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Livro(){}

    public Livro(Long id, String titulo, String autor, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
