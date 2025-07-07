// src/main/java/com/example/as_poo/service/FuncionarioService.java
package com.example.as_poo.service;

import com.example.as_poo.model.Empresa;
import com.example.as_poo.model.Funcionario;
import com.example.as_poo.repository.EmpresaRepository;
import com.example.as_poo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o id " + empresaId));
        funcionario.setEmpresa(empresa);
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioAtualizado.getNome());
                    funcionario.setCargo(funcionarioAtualizado.getCargo());
                    // Opcional: permitir a mudança de empresa
                    // if (funcionarioAtualizado.getEmpresa() != null && funcionarioAtualizado.getEmpresa().getId() != null) {
                    //    Empresa novaEmpresa = empresaRepository.findById(funcionarioAtualizado.getEmpresa().getId())
                    //            .orElseThrow(() -> new RuntimeException("Nova empresa não encontrada."));
                    //    funcionario.setEmpresa(novaEmpresa);
                    // }
                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com o id " + id));
    }

    public void deletar(Long id) {
        if(!funcionarioRepository.existsById(id)){
            throw new RuntimeException("Funcionário não encontrado com o id " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}