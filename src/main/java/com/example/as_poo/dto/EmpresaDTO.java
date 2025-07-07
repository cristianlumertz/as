// src/main/java/com/example/as_poo/dto/EmpresaDTO.java
package com.example.as_poo.dto;

import java.util.List;

public class EmpresaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private List<FuncionarioDTO> funcionarios;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public List<FuncionarioDTO> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<FuncionarioDTO> funcionarios) { this.funcionarios = funcionarios; }
}