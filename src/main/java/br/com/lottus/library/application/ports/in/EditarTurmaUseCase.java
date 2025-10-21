package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.domain.entities.Turma;

public interface EditarTurmaUseCase {
    Turma executar(Long id, GerenciarTurmaCommand command);
}
