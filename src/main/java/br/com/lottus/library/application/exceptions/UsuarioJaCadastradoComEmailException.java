package br.com.lottus.library.application.exceptions;

public class UsuarioJaCadastradoComEmailException extends RuntimeException {
    public UsuarioJaCadastradoComEmailException(String message) {
        super(message);
    }
}

