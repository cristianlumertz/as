package com.suaempresa.seuprojetoapi.dto;

import com.suaempresa.seuprojetoapi.model.Empresa;
import com.suaempresa.seuprojetoapi.model.Funcionario;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) para a entidade Empresa.
 * Usado para controlar a saída de dados da API, evitando referências circulares
 * e expondo apenas os dados necessários.
 */
public class EmpresaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    // Para evitar loops de referência bidirecionais e carregar apenas o necessário,
    // a lista de funcionários é representada por DTOs de funcionários.
    private Set<FuncionarioDTO> funcionarios;

    /**
     * Construtor que mapeia uma entidade Empresa para um EmpresaDTO.
     * @param empresa A entidade Empresa a ser mapeada.
     */
    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.getId();
        this.nome = empresa.getNome();
        this.cnpj = empresa.getCnpj();
        // Mapeia a coleção de Funcionario para FuncionarioDTO
        if (empresa.getFuncionarios() != null) {
            this.funcionarios = empresa.getFuncionarios().stream()
                    .map(FuncionarioDTO::new) // Cria um FuncionarioDTO para cada Funcionario
                    .collect(Collectors.toSet());
        }
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Set<FuncionarioDTO> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<FuncionarioDTO> funcionarios) {
        this.funcionarios = funcionarios;
    }
}