package br.com.lottus.library.domain.exceptions;

public class NomeLivroVazioOuNuloException extends RuntimeException {
    public NomeLivroVazioOuNuloException() {
        super("Nome do livro não pode ser nulo ou vazio");
    }
}
