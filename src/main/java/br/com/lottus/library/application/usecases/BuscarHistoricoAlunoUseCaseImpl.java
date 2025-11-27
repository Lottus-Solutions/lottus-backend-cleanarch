package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.BuscarHistoricoAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;

import java.util.List;
import java.util.Objects;

public class
BuscarHistoricoAlunoUseCaseImpl implements BuscarHistoricoAlunoUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private final AlunoRepositoryPort alunoRepositoryPort;

    public BuscarHistoricoAlunoUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort, AlunoRepositoryPort alunoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
        this.alunoRepositoryPort = alunoRepositoryPort;
    }


    @Override
    public List<Emprestimo> executar(Long matricula) {
        Aluno aluno = alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);


        return emprestimoRepositoryPort.findByStatusEmprestimo(StatusEmprestimo.FINALIZADO)
                .stream()
                .filter(e -> Objects.equals(e.getAluno().getMatricula(), aluno.getMatricula()))
                .limit(7)
                .toList();
    }
}
