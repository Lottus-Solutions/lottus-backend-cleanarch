package br.com.lottus.library.domain.exceptions;

public class CorCategoriaVazioOuNuloException extends RuntimeException {
    public CorCategoriaVazioOuNuloException() {
        super("A cor da categoria não pode ser nula ou vazia.");
    }
}
