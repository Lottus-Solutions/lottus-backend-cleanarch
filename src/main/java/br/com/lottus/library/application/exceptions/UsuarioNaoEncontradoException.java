package br.com.lottus.library.application.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    private static final String MENSAGEM_PADRAO = "Usuario não encontrado!";
    public UsuarioNaoEncontradoException() {
        super(MENSAGEM_PADRAO);
    }
}
