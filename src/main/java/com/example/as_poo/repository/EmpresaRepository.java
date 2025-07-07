// src/main/java/com/example/as_poo/repository/EmpresaRepository.java
package com.example.as_poo.repository;

import com.example.as_poo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}