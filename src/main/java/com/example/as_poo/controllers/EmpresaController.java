// src/main/java/com/example/as_poo/controller/EmpresaController.java
package com.example.as_poo.controllers;

import com.example.as_poo.dto.EmpresaDTO;
import com.example.as_poo.dto.FuncionarioDTO;
import com.example.as_poo.model.Empresa;
import com.example.as_poo.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Método para converter Entidade para DTO
    private EmpresaDTO toDTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(empresa.getId());
        dto.setNome(empresa.getNome());
        dto.setCnpj(empresa.getCnpj());
        if (empresa.getFuncionarios() != null) {
            dto.setFuncionarios(empresa.getFuncionarios().stream().map(func -> {
                FuncionarioDTO funcDto = new FuncionarioDTO();
                funcDto.setId(func.getId());
                funcDto.setNome(func.getNome());
                funcDto.setCargo(func.getCargo());
                return funcDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarTodas() {
        List<EmpresaDTO> list = empresaService.listarTodas().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id)
                .map(empresa -> ResponseEntity.ok(toDTO(empresa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaService.salvar(empresa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaEmpresa.getId()).toUri();
        return ResponseEntity.created(location).body(novaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        try {
            Empresa empresaAtualizada = empresaService.atualizar(id, empresa);
            return ResponseEntity.ok(empresaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            empresaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}