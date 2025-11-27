package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ListarTurmasUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class ListarTurmasUseCaseImpl implements ListarTurmasUseCase {

    private final TurmaRepositoryPort turmaRepositoryPort;

    public ListarTurmasUseCaseImpl(TurmaRepositoryPort turmaRepositoryPort) {
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    @Cacheable("turmas")
    public List<Turma> executar() {
        return turmaRepositoryPort.findAll();
    }
}
