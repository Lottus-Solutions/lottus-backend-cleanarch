package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.CredenciaisInvalidasExceptions;
import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException;
import br.com.lottus.library.domain.exceptions.EmailInvalidoException;
import br.com.lottus.library.domain.exceptions.NomeInvalidoException;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import br.com.lottus.library.application.exceptions.CategoriaJaExistenteException;
import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.exceptions.LivroJaCadastradoException;
import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerHandler {
    @ExceptionHandler(UsuarioJaCadastradoComEmailException.class)
    public ResponseEntity<Map<String, String>> emailJaCadastrado(UsuarioJaCadastradoComEmailException ex) {
        Map<String, String> error = Map.of(
                "Erro: ", "Erro de duplicidade: %s".formatted(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(CategoriaJaExistenteException.class)
    public ResponseEntity<Map<String, String>> handleCategoriaJaExistente(CategoriaJaExistenteException exception){
        log.warn("Tentativa de cadastrar categoria já existente: {}", exception.getMessage());

        Map<String, String> error =Map.of(
                "erro", "Categoria já existente",
                "mensagem", exception.getMessage() != null ? exception.getMessage() : "A categoria informada já existe no sistema"
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CredenciaisInvalidasExceptions.class)
    public ResponseEntity<Map<String, String>> credenciaisInvalidas(CredenciaisInvalidasExceptions ex) {
        Map<String, String> error = Map.of(
                "Error: ", "Erro de credenciais: %s".formatted(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException exception) {
        log.warn("Categoria não encontrada: {}", exception.getMessage());

        Map<String, String> error = Map.of(
                "erro", "Categoria não encontrada",
                "mensagem", exception.getMessage() != null ? exception.getMessage() : "A categoria informada não foi encontrada no sistema"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LivroJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> handleLivroJaCadastrado(LivroJaCadastradoException exception){
        log.warn("Tentativa de cadastrar livro já existente: {}", exception.getMessage());

        Map<String, String> error =Map.of(
                "erro", "Livro já cadastrado",
                "mensagem", exception.getMessage() != null ? exception.getMessage() : "O livro informado já está cadastrado no sistema"
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleLivroNaoEncontrado(LivroNaoEncontradoException exception) {
        log.warn("Livro não encontrado: {}", exception.getMessage());

        Map<String, String> errorResponse = Map.of(
                "erro", "Livro não encontrado",
                "mensagem", exception.getMessage() != null ? exception.getMessage() : "O livro solicitado não foi encontrado no sistema"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
        log.error("Erro inesperado: {}", exception.getMessage(), exception);

        Map<String, String> error = Map.of(
                "erro", "Erro interno do servidor",
                "mensagem", exception.getMessage() != null ? exception.getMessage() : "Ocorreu um erro inesperado"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler({NomeInvalidoException.class, EmailInvalidoException.class, SenhaInvalidaException.class, IdAvatarInvalidoException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(RuntimeException ex) {
        Map<String, String> error = Map.of(
                "Erro de validação: ", ex.getMessage() != null ? ex.getMessage() : "Ocorreu um erro de validação",
                "erro", "Erro interno do servidor",
                "mensagem", ex.getMessage() != null ? ex.getMessage() : "Ocorreu um erro inesperado"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }


}
