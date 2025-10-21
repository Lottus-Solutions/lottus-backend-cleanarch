package br.com.lottus.library.application.exceptions;

public class TurmaJaCadastradaException extends RuntimeException {
    public TurmaJaCadastradaException() {
        super("Uma turma com esta série já foi cadastrada.");
    }
}
