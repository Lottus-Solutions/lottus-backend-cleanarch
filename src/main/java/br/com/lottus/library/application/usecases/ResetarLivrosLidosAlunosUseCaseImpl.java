package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ResetarLivrosLidosAlunosUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;

import java.util.List;

public class ResetarLivrosLidosAlunosUseCaseImpl implements ResetarLivrosLidosAlunosUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public ResetarLivrosLidosAlunosUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public void executar() {
        List<Aluno> alunos = alunoRepositoryPort.findAll();
        for (Aluno aluno : alunos) {
            aluno.resetarLivrosLidos();
            alunoRepositoryPort.save(aluno);
        }
    }
}
