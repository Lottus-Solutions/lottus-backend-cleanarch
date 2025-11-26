package br.com.lottus.library.application.exceptions;

public class CategoriaJaExistenteException extends RuntimeException {
    public CategoriaJaExistenteException() {
        super("Já existe uma categoria com esse nome.");
    }
}
