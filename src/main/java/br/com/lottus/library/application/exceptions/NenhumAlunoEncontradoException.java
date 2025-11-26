package br.com.lottus.library.application.exceptions;

public class NenhumAlunoEncontradoException extends RuntimeException {
    public NenhumAlunoEncontradoException() {
        super("Nenhum aluno foi encontrado para os critérios fornecidos.");
    }
}
