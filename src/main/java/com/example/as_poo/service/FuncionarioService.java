package com.suaempresa.seuprojetoapi.service;

import com.suaempresa.seuprojetoapi.model.Empresa;
import com.suaempresa.seuprojetoapi.model.Funcionario;
import com.suaempresa.seuprojetoapi.repository.EmpresaRepository;
import com.suaempresa.seuprojetoapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço para a entidade Funcionario.
 * Contém a lógica de negócio e interage com os repositórios.
 */
@Service // Marca a classe como um componente de serviço
public class FuncionarioService {

    @Autowired // Injeta uma instância de FuncionarioRepository
    private FuncionarioRepository funcionarioRepository;

    @Autowired // Injeta uma instância de EmpresaRepository (necessário para associar funcionários a empresas)
    private EmpresaRepository empresaRepository;

    /**
     * Retorna todos os funcionários.
     * @return Uma lista de todos os funcionários.
     */
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    /**
     * Busca um funcionário pelo seu ID.
     * @param id O ID do funcionário.
     * @return Um Optional contendo o funcionário, se encontrado.
     */
    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    /**
     * Salva um novo funcionário e o associa a uma empresa existente.
     * @param empresaId O ID da empresa à qual o funcionário será associado.
     * @param funcionario O funcionário a ser salvo.
     * @return O funcionário salvo, ou null se a empresa não for encontrada.
     */
    public Funcionario save(Long empresaId, Funcionario funcionario) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(empresaId);
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            funcionario.setEmpresa(empresa); // Associa o funcionário à empresa
            return funcionarioRepository.save(funcionario);
        } else {
            // Lançar uma exceção personalizada seria uma prática melhor
            return null; // Empresa não encontrada
        }
    }

    /**
     * Deleta um funcionário pelo seu ID.
     * @param id O ID do funcionário a ser deletado.
     */
    public void deleteById(Long id) {
        funcionarioRepository.deleteById(id);
    }

    /**
     * Atualiza os detalhes de um funcionário existente.
     * @param id O ID do funcionário a ser atualizado.
     * @param funcionarioDetails Os novos detalhes do funcionário.
     * @return O funcionário atualizado, ou null se não encontrado.
     */
    public Funcionario update(Long id, Funcionario funcionarioDetails) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNome(funcionarioDetails.getNome());
            funcionario.setCargo(funcionarioDetails.getCargo());
            funcionario.setSalario(funcionarioDetails.getSalario());
            // A empresa do funcionário não é alterada por este método PUT,
            // pois isso geralmente seria feito por um endpoint específico de associação/desassociação.
            return funcionarioRepository.save(funcionario);
        } else {
            // Lançar uma exceção personalizada seria uma prática melhor
            return null;
        }
    }

    /**
     * Busca todos os funcionários de uma empresa específica.
     * @param empresaId O ID da empresa.
     * @return Uma lista de funcionários da empresa.
     */
    public List<Funcionario> findByEmpresaId(Long empresaId) {
        return funcionarioRepository.findByEmpresaId(empresaId);
    }
}
