package br.com.lottus.library.domain.exceptions;

public class NomeVazioOuNuloException extends RuntimeException {
    public NomeVazioOuNuloException() {
        super("Nome não pode ser vazio ou nulo");
    }
}
