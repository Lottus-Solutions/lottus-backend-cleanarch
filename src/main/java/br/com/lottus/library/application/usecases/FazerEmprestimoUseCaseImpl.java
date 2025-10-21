package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoJaPossuiEmprestimoAtivoException;
import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import br.com.lottus.library.application.ports.command.FazerEmprestimoCommand;
import br.com.lottus.library.application.ports.in.FazerEmprestimoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.Livro;

import java.time.LocalDate;

public class FazerEmprestimoUseCaseImpl implements FazerEmprestimoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;
    private final LivroRepositoryPort livroRepositoryPort;
    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private static final int DIAS_PARA_DEVOLUCAO = 15;

    public FazerEmprestimoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort, LivroRepositoryPort livroRepositoryPort, EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.livroRepositoryPort = livroRepositoryPort;
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    public Emprestimo executar(FazerEmprestimoCommand command) {
        Aluno aluno = alunoRepositoryPort.findById(command.matriculaAluno())
                .orElseThrow(AlunoNaoEncontradoException::new);

        if (!aluno.podeFazerEmprestimo()) {
            throw new AlunoJaPossuiEmprestimoAtivoException();
        }

        Livro livro = livroRepositoryPort.findById(command.livroId())
                .orElseThrow(LivroNaoEncontradoException::new);

        livro.emprestar();

        LocalDate dataDevolucao = command.dataEmprestimo().plusDays(DIAS_PARA_DEVOLUCAO);
        Emprestimo emprestimo = Emprestimo.criar(aluno, livro, command.dataEmprestimo(), dataDevolucao);

        livroRepositoryPort.save(livro);
        return emprestimoRepositoryPort.save(emprestimo);
    }
}
