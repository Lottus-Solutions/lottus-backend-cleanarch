package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.application.usecases.CadastrarCategoriaImpl;
import br.com.lottus.library.application.usecases.ListarCategoriaImpl;
import br.com.lottus.library.domain.entities.Categoria;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CadastrarCategoriaImpl cadastrarCategoria;
    private final ListarCategoriaImpl listarCategoria;

    public CategoriaController(CadastrarCategoriaImpl cadastrarCategoria,
                               ListarCategoriaImpl listarCategoria) {
        this.cadastrarCategoria = cadastrarCategoria;
        this.listarCategoria = listarCategoria;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody CadastrarCategoriaCommand command) {
        Categoria categoriaCadastrada = cadastrarCategoria.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = listarCategoria.executar();
        return ResponseEntity.ok().body(categorias);
    }
}
