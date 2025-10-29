
package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.*;
import br.com.lottus.library.domain.exceptions.*;
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

    @ExceptionHandler(AutorLivroVazioOuNuloException.class)
    public ResponseEntity<ErrorResponse> handleAutorLivroVazioOuNulo(AutorLivroVazioOuNuloException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "O nome do autor não pode ser nulo ou vazio", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(CorCategoriaVazioOuNuloException.class)
    public ResponseEntity<ErrorResponse> handleCorCategoriaVazioOuNulo(CorCategoriaVazioOuNuloException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "A categoria não deve ser nula ou vazia", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DescricaoLivroMuitoLongaException.class)
    public ResponseEntity<ErrorResponse> handleDescricaoLivroMuitoLonga(DescricaoLivroMuitoLongaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "A descrição não pode ter mais de 500 caracteres", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleEmailInvalidoException(EmailInvalidoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Email inválido", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(FinalidadeNaoInformadaException.class)
    public ResponseEntity<ErrorResponse> handleFinalidadeNaoInformada(FinalidadeNaoInformadaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "A finalidade do arquivo não foi informada.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NomeCategoriaVazioOuNuloException.class)
    public ResponseEntity<ErrorResponse> handleNomeCategoriaVazioOuNulo(NomeCategoriaVazioOuNuloException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Nome não pode ser nulo ou vazio.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NomeLivroVazioOuNuloException.class)
    public ResponseEntity<ErrorResponse> handleNomeLivroVazioOuNulo(NomeCategoriaVazioOuNuloException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Nome da categoria não pode ser nulo ou vazio.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(QuantidadeDisponivelInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleQuantidadeDisponivelInvalida(QuantidadeDisponivelInvalidaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Quantidade disponível inválida.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(QuantidadeLivroInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleQuantidadeLivroInvalida(QuantidadeLivroInvalidaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "A quantidade do livro não pode ser nula ou menor que um.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(StatusInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleStatusInvalidoException(StatusInvalidoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Status inválido", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(AlunoJaPossuiEmprestimoAtivoException.class)
    public ResponseEntity<ErrorResponse> handleAlunoJaPossuiEmprestimoAtivo(AlunoJaPossuiEmprestimoAtivoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "O aluno já possui um empréstimo ativo e não pode realizar outro.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleAlunoNaoEncontrado(AlunoNaoEncontradoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Aluno não encontrado.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EmprestimoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEmprestimoNaoEncontrado(EmprestimoNaoEncontradoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Empréstimo não encontrado.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(FormatoArquivoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleFormatoArquivoInvalidoException(FormatoArquivoInvalidoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Formato do arquivo inválido.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(LivroIndisponivelException.class)
    public ResponseEntity<ErrorResponse> handleLivroIndisponivel(LivroIndisponivelException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Não há unidades disponíveis deste livro para empréstimo.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NenhumAlunoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNenhumAlunoEncontrado(NenhumAlunoEncontradoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Nenhum aluno foi encontrado para os critérios fornecidos.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(TurmaJaCadastradaException.class)
    public ResponseEntity<ErrorResponse> handleTurmaJaCadastrada(TurmaJaCadastradaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Uma turma com esta série já foi cadastrada.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(TurmaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleTurmaNaoEncontrada(TurmaNaoEncontradaException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Turma não encontrada.", e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException e, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Usuario não encontrado!",e.getMessage(), request.getRequestURI());
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

