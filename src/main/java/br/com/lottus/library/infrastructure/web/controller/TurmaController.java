package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.application.ports.in.*;
import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.web.command.ObterOuCadastrarTurmaRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Turmas", description = "Endpoint para o gerenciamento de turmas")
@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final AdicionarTurmaUseCase adicionarTurmaUseCase;
    private final ListarTurmasUseCase listarTurmasUseCase;
    private final RemoverTurmaUseCase removerTurmaUseCase;
    private final EditarTurmaUseCase editarTurmaUseCase;
    private final ObterOuCadastrarTurmaUseCase obterOuCadastrarTurmaUseCase;


    @PostMapping
    public ResponseEntity<Turma> adicionar(@Valid @RequestBody GerenciarTurmaCommand command) {
        Turma turma = adicionarTurmaUseCase.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listar() {
        List<Turma> turmas = listarTurmasUseCase.executar();
        return ResponseEntity.ok(turmas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerTurmaUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> editar(@PathVariable Long id, @Valid @RequestBody GerenciarTurmaCommand command) {
        Turma turma = editarTurmaUseCase.executar(id, command);
        return ResponseEntity.ok(turma);
    }

    @PostMapping("/obter-ou-criar")
    public ResponseEntity<Turma> obterOuCriar(@RequestBody ObterOuCadastrarTurmaRequest request) {
        Turma turma = obterOuCadastrarTurmaUseCase.executar(request.toCommand());
        return ResponseEntity.ok(turma);
    }
}
