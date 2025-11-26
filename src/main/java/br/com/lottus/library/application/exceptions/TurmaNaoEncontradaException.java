package br.com.lottus.library.application.exceptions;

public class TurmaNaoEncontradaException extends RuntimeException {
    public TurmaNaoEncontradaException() {
        super("Turma não encontrada.");
    }
}
