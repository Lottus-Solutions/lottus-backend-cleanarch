package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.CredenciaisInvalidasExceptions;
import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException;
import br.com.lottus.library.domain.exceptions.EmailInvalidoException;
import br.com.lottus.library.domain.exceptions.NomeInvalidoException;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerHandler {
    @ExceptionHandler(UsuarioJaCadastradoComEmailException.class)
    public ResponseEntity<Map<String, String>> emailJaCadastrado(UsuarioJaCadastradoComEmailException ex) {
        Map<String, String> error = Map.of(
                "Erro: ", "Erro de duplicidade: %s".formatted(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CredenciaisInvalidasExceptions.class)
    public ResponseEntity<Map<String, String>> credenciaisInvalidas(CredenciaisInvalidasExceptions ex) {
        Map<String, String> error = Map.of(
                "Error: ", "Erro de credenciais: %s".formatted(ex.getMessage())
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler({NomeInvalidoException.class, EmailInvalidoException.class, SenhaInvalidaException.class, IdAvatarInvalidoException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(RuntimeException ex) {
        Map<String, String> error = Map.of(
                "Erro de validação: ", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
