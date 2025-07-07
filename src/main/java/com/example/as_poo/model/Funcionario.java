package com.suaempresa.seuprojetoapi.model;

import jakarta.persistence.*;

/**
 * Entidade Funcionario, representando um funcionário no sistema.
 * Possui um relacionamento Many-to-One com Empresa.
 */
@Entity // Marca a classe como uma entidade JPA
public class Funcionario {

    @Id // Marca o campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática de ID
    private Long id;
    private String nome;
    private String cargo;
    private double salario;

    // Relacionamento Many-to-One com Empresa
    // FetchType.LAZY: A empresa será carregada apenas quando for acessada (evita carregamento desnecessário)
    // @JoinColumn: Define a coluna de chave estrangeira no banco de dados (empresa_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Construtor padrão (necessário para JPA)
    public Funcionario() {
    }

    // Construtor com parâmetros
    public Funcionario(String nome, String cargo, double salario, Empresa empresa) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.empresa = empresa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
