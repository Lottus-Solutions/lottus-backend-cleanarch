package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.application.usecases.CadastrarCategoriaImpl;
import br.com.lottus.library.domain.entities.Categoria;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CadastrarCategoriaImpl cadastrarCategoria;

    public CategoriaController(CadastrarCategoriaImpl cadastrarCategoria) {
        this.cadastrarCategoria = cadastrarCategoria;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@Valid @RequestBody CadastrarCategoriaCommand command) {
        Categoria categoriaCadastrada = cadastrarCategoria.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCadastrada);
    }
}
