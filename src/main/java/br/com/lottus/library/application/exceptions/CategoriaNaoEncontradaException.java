package br.com.lottus.library.application.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException() {
        super("Categoria não encontrada!");
    }
}
