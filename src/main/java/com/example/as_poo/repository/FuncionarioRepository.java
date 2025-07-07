package com.suaempresa.seuprojetoapi.repository;

import com.suaempresa.seuprojetoapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repositório para a entidade Funcionario.
 * Estende JpaRepository para fornecer operações CRUD básicas.
 */
@Repository // Marca a interface como um componente de repositório
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    /**
     * Encontra todos os funcionários associados a uma empresa específica.
     * O Spring Data JPA gera a implementação deste método automaticamente.
     * @param empresaId O ID da empresa.
     * @return Uma lista de funcionários.
     */
    List<Funcionario> findByEmpresaId(Long empresaId);
}
