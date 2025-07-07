package com.suaempresa.seuprojetoapi.repository;

import com.suaempresa.seuprojetoapi.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Empresa.
 * Estende JpaRepository para fornecer operações CRUD básicas.
 */
@Repository // Marca a interface como um componente de repositório
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    // JpaRepository já fornece métodos como save, findById, findAll, deleteById
    // Você pode adicionar métodos personalizados aqui se precisar de consultas específicas
}
