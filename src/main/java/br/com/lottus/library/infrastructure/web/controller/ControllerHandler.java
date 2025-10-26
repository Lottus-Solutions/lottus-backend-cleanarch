
package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.*;
import br.com.lottus.library.domain.exceptions.EmailInvalidoException;
import br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException;
import br.com.lottus.library.domain.exceptions.NomeInvalidoException;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import br.com.lottus.library.infrastructure.web.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(UsuarioJaCadastradoComEmailException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioJaCadastrado(UsuarioJaCadastradoComEmailException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Usuário já cadastrado", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CategoriaJaExistenteException.class)
    public ResponseEntity<ErrorResponse> handleCategoriaJaExistente(CategoriaJaExistenteException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Categoria já existente", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CredenciaisInvalidasExceptions.class)
    public ResponseEntity<ErrorResponse> handleCredenciaisInvalidas(CredenciaisInvalidasExceptions ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Credenciais inválidas", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleCategoriaNaoEncontrada(CategoriaNaoEncontradaException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Categoria não encontrada", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(LivroJaCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleLivroJaCadastrado(LivroJaCadastradoException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Livro já cadastrado", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleLivroNaoEncontrado(LivroNaoEncontradoException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Livro não encontrado", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({NomeInvalidoException.class, EmailInvalidoException.class, SenhaInvalidaException.class, IdAvatarInvalidoException.class})
    public ResponseEntity<ErrorResponse> handleValidationExceptions(RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Erro de validação", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Erro inesperado: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor", "Ocorreu um erro inesperado.", request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String error, String message, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(message)
                .path(path)
                .build();
        log.info("Erro tratado: status={}, erro='{}', mensagem='{}', path='{}'", status.value(), error, message, path);
        return new ResponseEntity<>(errorResponse, status);
    }
}

