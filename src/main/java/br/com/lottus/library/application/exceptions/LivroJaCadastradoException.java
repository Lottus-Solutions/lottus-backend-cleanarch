package br.com.lottus.library.application.exceptions;

public class LivroJaCadastradoException extends RuntimeException {
    public LivroJaCadastradoException() {
        super("Livro já cadastrado.");
    }
}
