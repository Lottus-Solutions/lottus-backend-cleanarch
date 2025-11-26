package br.com.lottus.library.domain.exceptions;

public class NomeCategoriaVazioOuNuloException extends RuntimeException {
    public NomeCategoriaVazioOuNuloException() {
        super("Nome não pode ser nulo ou vazio");
    }
}
