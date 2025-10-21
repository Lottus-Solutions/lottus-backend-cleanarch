package br.com.lottus.library.application.exceptions;

public class AlunoJaPossuiEmprestimoAtivoException extends RuntimeException {
    public AlunoJaPossuiEmprestimoAtivoException() {
        super("O aluno já possui um empréstimo ativo e não pode realizar outro.");
    }
}
