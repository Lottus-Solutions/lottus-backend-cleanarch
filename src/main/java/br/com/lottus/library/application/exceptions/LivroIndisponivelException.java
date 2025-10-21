package br.com.lottus.library.application.exceptions;

public class LivroIndisponivelException extends RuntimeException {
    public LivroIndisponivelException() {
        super("Não há unidades disponíveis deste livro para empréstimo.");
    }
}
