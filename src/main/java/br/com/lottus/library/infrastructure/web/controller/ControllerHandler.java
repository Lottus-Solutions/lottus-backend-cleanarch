package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.CategoriaJaExistenteException;
import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.exceptions.LivroJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(CategoriaJaExistenteException.class)
    public ResponseEntity<Map<String, String>> categoriaJaExistenteException(CategoriaJaExistenteException exception){
        Map<String, String> error =Map.of(
                "error","Ocorreu um erro de duplicidade: %s".formatted(exception.getMessage())
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> categoriaNaoEncontradaException(CategoriaNaoEncontradaException exception) {
        Map<String, String> error = Map.of(
                "error", exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LivroJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> livroJaCadastradoException(LivroJaCadastradoException exception){
        Map<String, String> error =Map.of(
                "error","Ocorreu um erro de duplicidade: %s".formatted(exception.getMessage())
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
