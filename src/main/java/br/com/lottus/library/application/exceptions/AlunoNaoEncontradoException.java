package br.com.lottus.library.application.exceptions;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException() {
        super("Aluno não encontrado.");
    }
}
