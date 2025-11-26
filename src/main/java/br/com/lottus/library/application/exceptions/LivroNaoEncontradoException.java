package br.com.lottus.library.application.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException() {
        super("Livro não encontrado!");
    }
}
