package com.example.as_poo.controllers;

import com.example.as_poo.model.Empresa;
import com.suaempresa.seuprojetoapi.service.EmpresaService;
import com.suaempresa.seuprojetoapi.dto.EmpresaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para a entidade Empresa.
 * Define os endpoints para operações CRUD de Empresa.
 */
@RestController // Marca a classe como um controlador REST
@RequestMapping("/empresas") // Define o caminho base para os endpoints deste controlador
public class EmpresaController {

    @Autowired // Injeta uma instância de EmpresaService
    private EmpresaService empresaService;

    /**
     * GET /empresas
     * Retorna uma lista de todas as empresas.
     * @return ResponseEntity com a lista de EmpresaDTOs e status HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.findAll();
        // Converte a lista de entidades Empresa para uma lista de EmpresaDTOs
        List<EmpresaDTO> empresaDTOs = empresas.stream()
                .map(EmpresaDTO::new) // Usa o construtor do DTO para mapeamento
                .collect(Collectors.toList());
        return ResponseEntity.ok(empresaDTOs);
    }

    /**
     * GET /empresas/{id}
     * Retorna uma empresa específica pelo seu ID.
     * @param id O ID da empresa.
     * @return ResponseEntity com o EmpresaDTO e status HTTP 200 OK, ou 404 Not Found se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getEmpresaById(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.findById(id);
        // Mapeia o Optional para um ResponseEntity
        return empresa.map(e -> ResponseEntity.ok(new EmpresaDTO(e)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /empresas
     * Cria uma nova empresa.
     * @param empresa A entidade Empresa a ser criada (recebida do corpo da requisição).
     * @return ResponseEntity com o EmpresaDTO da empresa criada e status HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@RequestBody Empresa empresa) {
        Empresa savedEmpresa = empresaService.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EmpresaDTO(savedEmpresa));
    }

    /**
     * PUT /empresas/{id}
     * Atualiza uma empresa existente.
     * @param id O ID da empresa a ser atualizada.
     * @param empresaDetails A entidade Empresa com os detalhes atualizados.
     * @return ResponseEntity com o EmpresaDTO da empresa atualizada e status HTTP 200 OK, ou 404 Not Found se não encontrada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresaDetails) {
        Empresa updatedEmpresa = empresaService.update(id, empresaDetails);
        if (updatedEmpresa != null) {
            return ResponseEntity.ok(new EmpresaDTO(updatedEmpresa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /empresas/{id}
     * Deleta uma empresa pelo seu ID.
     * @param id O ID da empresa a ser deletada.
     * @return ResponseEntity com status HTTP 204 No Content se deletada com sucesso, ou 404 Not Found se não encontrada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.findById(id);
        if (empresa.isPresent()) {
            empresaService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content para deleção bem-sucedida
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}