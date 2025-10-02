package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarLivroCommand;
import br.com.lottus.library.application.usecases.CadastrarLivroImpl;
import br.com.lottus.library.domain.entities.Livro;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Livros", description = "Endpoint para o gerenciamento de livros")
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CadastrarLivroImpl cadastrarLivro;

    public LivroController(CadastrarLivroImpl cadastrarLivro) {
        this.cadastrarLivro = cadastrarLivro;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@Valid @RequestBody CadastrarLivroCommand command) {
        Livro livroCadastrado = cadastrarLivro.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }
}
