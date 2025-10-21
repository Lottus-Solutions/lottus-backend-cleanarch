package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.TurmaJaCadastradaException;
import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.application.ports.in.EditarTurmaUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;

public class EditarTurmaUseCaseImpl implements EditarTurmaUseCase {

    private final TurmaRepositoryPort turmaRepositoryPort;

    public EditarTurmaUseCaseImpl(TurmaRepositoryPort turmaRepositoryPort) {
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public Turma executar(Long id, GerenciarTurmaCommand command) {
        Turma turma = turmaRepositoryPort.findById(id)
                .orElseThrow(TurmaNaoEncontradaException::new);

        if (turmaRepositoryPort.existsByNomeIgnoreCase(command.nome())) {
            throw new TurmaJaCadastradaException();
        }

        turma.alterarNome(command.nome());

        return turmaRepositoryPort.save(turma);
    }
}
