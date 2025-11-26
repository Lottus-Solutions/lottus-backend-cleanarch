package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.application.ports.in.ObterOuCriarCategoriaUseCase;
import br.com.lottus.library.application.usecases.CadastrarCategoriaImpl;
import br.com.lottus.library.application.usecases.ListarCategoriaImpl;
import br.com.lottus.library.application.usecases.RemoverCategoriaUseCaseImpl;
import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.infrastructure.web.command.ObterOuCriarCategoriaRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CadastrarCategoriaImpl cadastrarCategoria;
    private final ListarCategoriaImpl listarCategoria;
    private final RemoverCategoriaUseCaseImpl removerCategoria;
    private final ObterOuCriarCategoriaUseCase obterOuCriarCategoriaUseCase;

    public CategoriaController(CadastrarCategoriaImpl cadastrarCategoria,
                               ListarCategoriaImpl listarCategoria,
                               RemoverCategoriaUseCaseImpl removerCategoria,
                               ObterOuCriarCategoriaUseCase obterOuCriarCategoriaUseCase) {
        this.cadastrarCategoria = cadastrarCategoria;
        this.listarCategoria = listarCategoria;
        this.removerCategoria = removerCategoria;
        this.obterOuCriarCategoriaUseCase = obterOuCriarCategoriaUseCase;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody CadastrarCategoriaCommand command) {
        Categoria categoriaCadastrada = cadastrarCategoria.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCadastrada);
    }

    @PostMapping("/obter-ou-criar")
    public ResponseEntity<Categoria> obterOuCriarCategoria(@RequestBody ObterOuCriarCategoriaRequest request) {
        Categoria categoria = obterOuCriarCategoriaUseCase.executar(request.toCommand());
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = listarCategoria.executar();
        return ResponseEntity.ok().body(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerCategoria.executar(id);
        return ResponseEntity.noContent().build();
    }
}
