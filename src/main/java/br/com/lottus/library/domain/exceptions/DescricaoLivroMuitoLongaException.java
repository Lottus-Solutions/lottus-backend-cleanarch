package br.com.lottus.library.domain.exceptions;

public class DescricaoLivroMuitoLongaException extends RuntimeException {
    public DescricaoLivroMuitoLongaException() {
        super("A descrição não pode ter mais de 500 caracteres");
    }
}
