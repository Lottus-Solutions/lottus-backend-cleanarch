package br.com.lottus.library.domain.exceptions;

public class CredenciaisInvalidas extends IllegalArgumentException {
    public CredenciaisInvalidas(String message) {
        super(message);
    }
}
