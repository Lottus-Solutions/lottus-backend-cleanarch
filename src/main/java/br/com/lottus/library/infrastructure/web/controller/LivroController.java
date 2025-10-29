package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarLivroCommand;
import br.com.lottus.library.application.ports.in.AtualizarLivroUseCase;
import br.com.lottus.library.application.ports.in.BuscarLivrosUseCase;
import br.com.lottus.library.application.ports.in.CadastrarLivroUseCase;
import br.com.lottus.library.application.ports.in.RemoverLivroUseCase;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.web.command.AtualizarLivroCommand;
import br.com.lottus.library.infrastructure.web.dto.LivroResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Livros", description = "Endpoint para o gerenciamento de livros")
@RestController
@RequestMapping("/livros")
@Slf4j
public class LivroController {

    private final CadastrarLivroUseCase cadastrarLivro;
    private final BuscarLivrosUseCase buscarLivro;
    private final RemoverLivroUseCase removerLivro;
    private final AtualizarLivroUseCase atualizarLivro;

    public LivroController(CadastrarLivroUseCase cadastrarLivro, BuscarLivrosUseCase buscarLivro, RemoverLivroUseCase removerLivro, AtualizarLivroUseCase atualizarLivro) {
        this.cadastrarLivro = cadastrarLivro;
        this.buscarLivro = buscarLivro;
        this.removerLivro = removerLivro;
        this.atualizarLivro = atualizarLivro;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@Valid @RequestBody CadastrarLivroCommand command) {
        log.info("Requisição recebida para cadastrar livro: {}", command.nome());
        Livro livroCadastrado = cadastrarLivro.executar(command);

        log.info("Livro cadastrado com sucesso: ID = {}, Nome = {}", livroCadastrado.getId(), livroCadastrado.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> buscar(
            @RequestParam(value = "valor", required = false) String valor,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") int tamanho
    ) {
        Page<LivroResponseDTO> livros = buscarLivro.executar(valor, status, categoriaId, pagina, tamanho);
        return ResponseEntity.ok(livros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        log.info("Requisição recebida para remover livro (ID: {})", id);
        removerLivro.executar(id);

        log.info("Livro (ID: {}) removido com sucesso", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody AtualizarLivroCommand command) {
        log.info("Requisição recebida para atualizar livro (ID: {})", id);
        Livro livroAtualizado = atualizarLivro.executar(id, command);

        log.info("Livro (ID: {}) atualizado com sucesso e retornado ao cliente", id);
        return ResponseEntity.ok(livroAtualizado);
    }
}
