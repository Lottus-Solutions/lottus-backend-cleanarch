package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.in.CadastrarAlunosEmLoteUseCase;
import br.com.lottus.library.application.ports.in.CadastrarLivrosEmLoteUseCase;
import br.com.lottus.library.infrastructure.web.dto.AlunoBatchDTO;
import br.com.lottus.library.infrastructure.web.dto.LivroBatchDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Batch", description = "Endpoints para operações em lote (Bulk Insert)")
@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchController {

    private final CadastrarLivrosEmLoteUseCase cadastrarLivrosEmLoteUseCase;
    private final CadastrarAlunosEmLoteUseCase cadastrarAlunosEmLoteUseCase;

    @Operation(summary = "Cadastra livros em lote", description = "Recebe uma lista de livros e realiza a inserção em massa")
    @PostMapping("/livros")
    public ResponseEntity<Void> cadastrarLivros(@RequestBody List<LivroBatchDTO> livros) {
        cadastrarLivrosEmLoteUseCase.cadastrarLivros(livros);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Cadastra alunos em lote", description = "Recebe uma lista de alunos e realiza a inserção em massa")
    @PostMapping("/alunos")
    public ResponseEntity<Void> cadastrarAlunos(@RequestBody List<AlunoBatchDTO> alunos) {
        cadastrarAlunosEmLoteUseCase.cadastrarAlunos(alunos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
