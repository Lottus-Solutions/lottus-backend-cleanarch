package br.com.lottus.library.domain.exceptions;

public class AutorLivroVazioOuNuloException extends RuntimeException {
    public AutorLivroVazioOuNuloException() {
        super("Nome do autor não pode ser nulo ou vazio");
    }
}
