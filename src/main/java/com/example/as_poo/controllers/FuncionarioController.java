package com.suaempresa.seuprojetoapi.controller;

import com.suaempresa.seuprojetoapi.model.Funcionario;
import com.suaempresa.seuprojetoapi.service.FuncionarioService;
import com.suaempresa.seuprojetoapi.dto.FuncionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para a entidade Funcionario.
 * Define os endpoints para operações CRUD de Funcionario.
 */
@RestController // Marca a classe como um controlador REST
@RequestMapping("/funcionarios") // Define o caminho base para os endpoints deste controlador
public class FuncionarioController {

    @Autowired // Injeta uma instância de FuncionarioService
    private FuncionarioService funcionarioService;

    /**
     * GET /funcionarios
     * Retorna uma lista de todos os funcionários.
     * @return ResponseEntity com a lista de FuncionarioDTOs e status HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.findAll();
        // Converte a lista de entidades Funcionario para uma lista de FuncionarioDTOs
        List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
                .map(FuncionarioDTO::new) // Usa o construtor do DTO para mapeamento
                .collect(Collectors.toList());
        return ResponseEntity.ok(funcionarioDTOs);
    }

    /**
     * GET /funcionarios/{id}
     * Retorna um funcionário específico pelo seu ID.
     * @param id O ID do funcionário.
     * @return ResponseEntity com o FuncionarioDTO e status HTTP 200 OK, ou 404 Not Found se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> getFuncionarioById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        // Mapeia o Optional para um ResponseEntity
        return funcionario.map(f -> ResponseEntity.ok(new FuncionarioDTO(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /funcionarios/empresa/{empresaId}
     * Cria um novo funcionário e o associa a uma empresa existente.
     * @param empresaId O ID da empresa à qual o funcionário será associado.
     * @param funcionario A entidade Funcionario a ser criada.
     * @return ResponseEntity com o FuncionarioDTO do funcionário criado e status HTTP 201 Created,
     * ou 404 Not Found se a empresa não for encontrada.
     */
    @PostMapping("/empresa/{empresaId}")
    public ResponseEntity<FuncionarioDTO> createFuncionario(@PathVariable Long empresaId, @RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.save(empresaId, funcionario);
        if (savedFuncionario != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new FuncionarioDTO(savedFuncionario));
        } else {
            return ResponseEntity.notFound().build(); // Empresa não encontrada
        }
    }

    /**
     * PUT /funcionarios/{id}
     * Atualiza um funcionário existente.
     * @param id O ID do funcionário a ser atualizado.
     * @param funcionarioDetails A entidade Funcionario com os detalhes atualizados.
     * @return ResponseEntity com o FuncionarioDTO do funcionário atualizado e status HTTP 200 OK, ou 404 Not Found se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetails) {
        Funcionario updatedFuncionario = funcionarioService.update(id, funcionarioDetails);
        if (updatedFuncionario != null) {
            return ResponseEntity.ok(new FuncionarioDTO(updatedFuncionario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /funcionarios/{id}
     * Deleta um funcionário pelo seu ID.
     * @param id O ID do funcionário a ser deletado.
     * @return ResponseEntity com status HTTP 204 No Content se deletado com sucesso, ou 404 Not Found se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.findById(id);
        if (funcionario.isPresent()) {
            funcionarioService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content para deleção bem-sucedida
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /funcionarios/empresa/{empresaId}
     * Retorna uma lista de funcionários de uma empresa específica.
     * @param empresaId O ID da empresa.
     * @return ResponseEntity com a lista de FuncionarioDTOs e status HTTP 200 OK.
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<FuncionarioDTO>> getFuncionariosByEmpresaId(@PathVariable Long empresaId) {
        List<Funcionario> funcionarios = funcionarioService.findByEmpresaId(empresaId);
        // Converte a lista de entidades Funcionario para uma lista de FuncionarioDTOs
        List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
                .map(FuncionarioDTO::new) // Usa o construtor do DTO para mapeamento
                .collect(Collectors.toList());
        return ResponseEntity.ok(funcionarioDTOs);
    }
}