package com.suaempresa.seuprojetoapi.service;

import com.suaempresa.seuprojetoapi.model.Empresa;
import com.suaempresa.seuprojetoapi.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço para a entidade Empresa.
 * Contém a lógica de negócio e interage com o repositório.
 */
@Service // Marca a classe como um componente de serviço
public class EmpresaService {

    @Autowired // Injeta uma instância de EmpresaRepository
    private EmpresaRepository empresaRepository;

    /**
     * Retorna todas as empresas.
     * @return Uma lista de todas as empresas.
     */
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    /**
     * Busca uma empresa pelo seu ID.
     * @param id O ID da empresa.
     * @return Um Optional contendo a empresa, se encontrada.
     */
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    /**
     * Salva uma nova empresa ou atualiza uma existente.
     * @param empresa A empresa a ser salva.
     * @return A empresa salva/atualizada.
     */
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    /**
     * Deleta uma empresa pelo seu ID.
     * @param id O ID da empresa a ser deletada.
     */
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }

    /**
     * Atualiza os detalhes de uma empresa existente.
     * @param id O ID da empresa a ser atualizada.
     * @param empresaDetails Os novos detalhes da empresa.
     * @return A empresa atualizada, ou null se não encontrada.
     */
    public Empresa update(Long id, Empresa empresaDetails) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            empresa.setNome(empresaDetails.getNome());
            empresa.setCnpj(empresaDetails.getCnpj());
            // Nota: Atualizar funcionários diretamente aqui pode ser complexo devido ao relacionamento bidirecional
            // e ao carregamento lazy. Geralmente, funcionários são atualizados via seus próprios endpoints.
            return empresaRepository.save(empresa);
        } else {
            // Lançar uma exceção personalizada seria uma prática melhor em um ambiente de produção
            return null;
        }
    }
}
