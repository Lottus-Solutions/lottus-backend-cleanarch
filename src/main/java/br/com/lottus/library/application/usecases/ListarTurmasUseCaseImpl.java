package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ListarTurmasUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;

import java.util.List;

public class ListarTurmasUseCaseImpl implements ListarTurmasUseCase {

    private final TurmaRepositoryPort turmaRepositoryPort;

    public ListarTurmasUseCaseImpl(TurmaRepositoryPort turmaRepositoryPort) {
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public List<Turma> executar() {
        return turmaRepositoryPort.findAll();
    }
}
