// src/main/java/com/example/as_poo/dto/FuncionarioDTO.java
package com.example.as_poo.dto;

public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String cargo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}