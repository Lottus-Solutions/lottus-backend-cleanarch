package br.com.lottus.library.domain.exceptions;

public class FinalidadeNaoInformadaException extends RuntimeException {
    public FinalidadeNaoInformadaException() {
        super("A finalidade do arquivo não foi informada.");
    }
}