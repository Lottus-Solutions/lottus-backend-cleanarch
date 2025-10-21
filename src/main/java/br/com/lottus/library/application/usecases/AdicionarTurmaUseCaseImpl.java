package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.TurmaJaCadastradaException;
import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.application.ports.in.AdicionarTurmaUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;

public class AdicionarTurmaUseCaseImpl implements AdicionarTurmaUseCase {

    private final TurmaRepositoryPort turmaRepositoryPort;

    public AdicionarTurmaUseCaseImpl(TurmaRepositoryPort turmaRepositoryPort) {
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public Turma executar(GerenciarTurmaCommand command) {
        if (turmaRepositoryPort.existsByNomeIgnoreCase(command.nome())) {
            throw new TurmaJaCadastradaException();
        }

        Turma turma = Turma.criar(command.nome());

        return turmaRepositoryPort.save(turma);
    }
}
