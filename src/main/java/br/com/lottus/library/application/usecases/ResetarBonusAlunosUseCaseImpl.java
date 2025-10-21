package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ResetarBonusAlunosUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;

import java.util.List;

public class ResetarBonusAlunosUseCaseImpl implements ResetarBonusAlunosUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public ResetarBonusAlunosUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public void executar() {
        List<Aluno> alunos = alunoRepositoryPort.findAll();
        for (Aluno aluno : alunos) {
            aluno.resetarBonus();
            alunoRepositoryPort.save(aluno);
        }
    }
}
