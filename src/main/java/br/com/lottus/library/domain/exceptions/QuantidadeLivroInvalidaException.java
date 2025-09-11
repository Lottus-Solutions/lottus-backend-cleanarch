package br.com.lottus.library.domain.exceptions;

public class QuantidadeLivroInvalidaException extends RuntimeException {
    public QuantidadeLivroInvalidaException() {
        super("A quantidade do livro não pode ser nula ou menor que um");
    }
}
