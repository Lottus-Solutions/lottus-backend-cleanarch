package br.com.lottus.library.application.exceptions;

public class CredenciaisInvalidasExceptions extends RuntimeException {
    private static final String MENSAGEM_PADRAO = "Credenciais invalidas";
    public CredenciaisInvalidasExceptions() {
        super(MENSAGEM_PADRAO);
    }

    /**
     *
     * Construtor que aceita a causa original
     * Útil para registrar a exceção original que causou a falha de autenticação
     * @param cause A exceção original a ser encapsulada.
     */
    public CredenciaisInvalidasExceptions(Throwable cause) {
        super(MENSAGEM_PADRAO);
    }
}
