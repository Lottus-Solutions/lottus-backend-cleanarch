package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.AdicionarAlunoCommand;
import br.com.lottus.library.application.ports.command.EditarAlunoCommand;
import br.com.lottus.library.application.ports.in.*;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.web.dto.AlunoDTO;
import br.com.lottus.library.infrastructure.web.dto.PerfilAlunoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Alunos", description = "Endpoint para o gerenciamento de alunos")
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AdicionarAlunoUseCase adicionarAlunoUseCase;
    private final RemoverAlunoUseCase removerAlunoUseCase;
    private final EditarAlunoUseCase editarAlunoUseCase;
    private final ListarAlunosPorTurmaUseCase listarAlunosPorTurmaUseCase;
    private final BuscarAlunoPorMatriculaUseCase buscarAlunoPorMatriculaUseCase;
    private final ListarAlunosUseCase listarAlunosUseCase;
    private final ConstruirPerfilAlunoUseCase construirPerfilAlunoUseCase;
    private final ListarAlunosPorNomeUseCase listarAlunosPorNomeUseCase;
    private final BuscarAlunosPorNomeETurmaUseCase buscarAlunosPorNomeETurmaUseCase;
    private final ResetarBonusAlunosUseCase resetarBonusAlunosUseCase;
    private final ResetarLivrosLidosAlunosUseCase resetarLivrosLidosAlunosUseCase;
    private final ListarTurmasUseCase listarTurmasUseCase;

    @Operation(summary = "Cadastra um novo aluno", description = "Retorna o aluno cadastrado")
    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> adicionarAluno(@Valid @RequestBody AdicionarAlunoCommand command) {
        Aluno aluno = adicionarAlunoUseCase.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @Operation(summary = "Remover aluno pelo numero da Matricula", description = "Retorna uma mensagem informado sobre o resultado da operação")
    @DeleteMapping("/remover/{matricula}")
    public ResponseEntity<String> removerAluno(@PathVariable Long matricula) {
        removerAlunoUseCase.executar(matricula);
        return ResponseEntity.ok("Aluno removido com sucesso");
    }

    @Operation(summary = "Editar aluno pelo numero da Matricula", description = "Retorna uma mensagem informado sobre o resultado da operação")
    @PutMapping("/editar/{matricula}")
    public ResponseEntity<String> editarAluno(@PathVariable Long matricula, @Valid @RequestBody EditarAlunoCommand command) {
        editarAlunoUseCase.executar(matricula, command);
        return ResponseEntity.ok("Aluno editado com sucesso");
    }

    @Operation(summary = "Constrói perfil do aluno pelo numero da matricula", description = "Retorna as informaçoes para construção do perfil")
    @GetMapping("/perfil/{matricula}")
    public ResponseEntity<PerfilAlunoResponse> construirPerfil(@PathVariable Long matricula) {
        return ResponseEntity.ok(construirPerfilAlunoUseCase.executar(matricula));
    }

    @Operation(summary = "Obtem aluno pelo numero da Matricula", description = "Retorna o aluno encontrado")
    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoDTO> buscarPorMatricula(@PathVariable Long matricula) {
        Aluno aluno = buscarAlunoPorMatriculaUseCase.executar(matricula);
        return ResponseEntity.ok(toDTO(aluno));
    }

    @Operation(summary = "Obtem alunos de uma determinada turma", description = "Retorna os alunos encontrados")
    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<Page<AlunoDTO>> buscarPorTurma(
            @PathVariable Long turmaId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<AlunoDTO> alunos = listarAlunosPorTurmaUseCase.executar(turmaId, pageable)
                .map(this::toDTO);

        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Lista todos os alunos", description = "Retorna todos os alunos cadastrados")
    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar() {
        return ResponseEntity.ok(listarAlunosUseCase.executar().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @Operation(summary = "Lista alunos por nome", description = "Retorna todos os alunos com o nome informado")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AlunoDTO>> listarAlunosPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(listarAlunosPorNomeUseCase.executar(nome).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    @Operation(summary = "Lista todas as turmas", description = "Retorna todas as turmas cadastradas")
    @GetMapping("/listar-turmas")
    public ResponseEntity<List<Turma>> listarTurmas() {
        return ResponseEntity.ok(listarTurmasUseCase.executar());
    }

    @Operation(summary = "Busca alunos por nome e turma", description = "Retorna uma lista dos alunos encontrados")
    @GetMapping("/buscar-aluno-nome-turma/{turmaId}/{nome}")
    public ResponseEntity<List<AlunoDTO>> buscarAlunosPorNomeETurma(@PathVariable String nome, @PathVariable Long turmaId) {
        return ResponseEntity.ok(buscarAlunosPorNomeETurmaUseCase.executar(nome, turmaId).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    // Assuming these endpoints, as they were not in the provided controller
    @PostMapping("/reset-bonus")
    public ResponseEntity<Void> resetarBonus() {
        resetarBonusAlunosUseCase.executar();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-livros-lidos")
    public ResponseEntity<Void> resetarLivrosLidos() {
        resetarLivrosLidosAlunosUseCase.executar();
        return ResponseEntity.ok().build();
    }

    private AlunoDTO toDTO(Aluno aluno) {
        String livroAtual = aluno.getEmprestimos().stream()
                .filter(e -> e.getStatusEmprestimo() == br.com.lottus.library.domain.entities.StatusEmprestimo.ATIVO || e.getStatusEmprestimo() == br.com.lottus.library.domain.entities.StatusEmprestimo.ATRASADO)
                .findFirst()
                .map(e -> e.getLivro().getNome())
                .orElse(null);

        return new AlunoDTO(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getQtdBonus(),
                aluno.getTurma().getId(),
                aluno.getQtdLivrosLidos(),
                livroAtual
        );
    }
}
