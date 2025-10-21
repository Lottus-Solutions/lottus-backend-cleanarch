package br.com.lottus.library.application.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException() {
        super("Empréstimo não encontrado.");
    }
}
