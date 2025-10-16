package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarLivroCommand;
import br.com.lottus.library.application.ports.in.RemoverLivroUseCase;
import br.com.lottus.library.application.usecases.CadastrarLivroImpl;
import br.com.lottus.library.application.usecases.ListarLivrosUseCaseImpl;
import br.com.lottus.library.domain.entities.Livro;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Livros", description = "Endpoint para o gerenciamento de livros")
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroImpl cadastrarLivro;
    private final ListarLivrosUseCaseImpl listarLivro;
    private final RemoverLivroUseCase removerLivro;

    public LivroController(CadastrarLivroImpl cadastrarLivro, ListarLivrosUseCaseImpl listarLivro, RemoverLivroUseCase removerLivro) {
        this.cadastrarLivro = cadastrarLivro;
        this.listarLivro = listarLivro;
        this.removerLivro = removerLivro;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@Valid @RequestBody CadastrarLivroCommand command) {
        Livro livroCadastrado = cadastrarLivro.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(listarLivro.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerLivro.executar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
