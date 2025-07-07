// src/main/java/com/example/as_poo/service/EmpresaService.java
package com.example.as_poo.service;

import com.example.as_poo.model.Empresa;
import com.example.as_poo.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Long id, Empresa empresaAtualizada) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setNome(empresaAtualizada.getNome());
                    empresa.setCnpj(empresaAtualizada.getCnpj());
                    return empresaRepository.save(empresa);
                })
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o id " + id));
    }

    public void deletar(Long id) {
        if(!empresaRepository.existsById(id)){
            throw new RuntimeException("Empresa não encontrada com o id " + id);
        }
        empresaRepository.deleteById(id);
    }
}