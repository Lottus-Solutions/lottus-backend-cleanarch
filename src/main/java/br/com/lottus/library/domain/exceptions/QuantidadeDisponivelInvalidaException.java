package br.com.lottus.library.domain.exceptions;

public class QuantidadeDisponivelInvalidaException extends RuntimeException {
    public QuantidadeDisponivelInvalidaException() {
        super("A quantidade de livro disponível não pode nula ou menor que um");
    }
}
