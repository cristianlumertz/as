// src/main/java/com/example/as_poo/repository/FuncionarioRepository.java
package com.example.as_poo.repository;

import com.example.as_poo.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}