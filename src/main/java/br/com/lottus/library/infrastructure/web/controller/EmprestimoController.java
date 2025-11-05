package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.FazerEmprestimoCommand;
import br.com.lottus.library.application.ports.in.*;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.infrastructure.web.dto.EmprestimoResponseDTO;
import br.com.lottus.library.infrastructure.web.dto.VerificarRenovadoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Empréstimos", description = "Endpoint para gerenciamento dos empréstimos")
@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final FazerEmprestimoUseCase fazerEmprestimoUseCase;
    private final FinalizarEmprestimoUseCase finalizarEmprestimoUseCase;
    private final RenovarEmprestimoUseCase renovarEmprestimoUseCase;
    private final ListarEmprestimosUseCase listarEmprestimosUseCase;
    private final BuscarHistoricoLivroUseCase buscarHistoricoLivroUseCase;
    private final BuscarHistoricoAlunoUseCase buscarHistoricoAlunoUseCase;
    private final FiltrarEmprestimosAtrasadosUseCase filtrarEmprestimosAtrasadosUseCase;
    private final VerificarQuantidadeRenovadoUseCase verificarQuantidadeRenovadoUseCase;

    @Operation(summary = "Realiza um empréstimo", description = "Retorna o empréstimo realizado")
    @PostMapping
    public ResponseEntity<Emprestimo> fazerEmprestimo(@Valid @RequestBody FazerEmprestimoCommand command) {
        Emprestimo emprestimo = fazerEmprestimoUseCase.executar(command);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(emprestimo.getId())
                .toUri();
        return ResponseEntity.created(location).body(emprestimo);
    }

    @Operation(summary = "Lista todos os empréstimos", description = "Retorna uma lista de todos os empréstimos")
    @GetMapping
    public ResponseEntity<Page<EmprestimoResponseDTO>> listarEmprestimos(
            @RequestParam(required = false) String busca,
            @RequestParam(required = false) Boolean atrasados,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        boolean filtroAtrasados = atrasados != null && atrasados;
        Page<EmprestimoResponseDTO> emprestimos = listarEmprestimosUseCase.executar(busca, filtroAtrasados, pageable);
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<Boolean> renovarEmprestimo(@PathVariable Long id) {
        renovarEmprestimoUseCase.executar(id);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Boolean> finalizarEmprestimo(@PathVariable Long id) {
        finalizarEmprestimoUseCase.executar(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}/verificar-quantidade-renovado")
    public ResponseEntity<VerificarRenovadoResponse> verificarQuantidadeRenovado(@PathVariable Long id) {
        return ResponseEntity.ok(verificarQuantidadeRenovadoUseCase.executar(id));
    }

    @GetMapping("/historico/livro/{id}")
    public ResponseEntity<List<Emprestimo>> buscarHistoricoLivro(@PathVariable Long id) {
        return ResponseEntity.ok(buscarHistoricoLivroUseCase.executar(id));
    }

    @GetMapping("/historico/aluno/{matricula}")
    public ResponseEntity<List<Emprestimo>> buscarHistoricoAluno(@PathVariable Long matricula) {
        return ResponseEntity.ok(buscarHistoricoAlunoUseCase.executar(matricula));
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<Emprestimo>> filtrarEmprestimosAtrasados() {
        return ResponseEntity.ok(filtrarEmprestimosAtrasadosUseCase.executar());
    }
}
