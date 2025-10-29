package br.com.lottus.library.domain.exceptions;

public class StatusInvalidoException extends RuntimeException {
    public StatusInvalidoException(String status) {
        super("Status inválido: " + status);
    }
}
