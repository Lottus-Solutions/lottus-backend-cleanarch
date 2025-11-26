package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.domain.entities.Turma;

public interface ObterOuCadastrarTurmaUseCase {
    Turma executar(GerenciarTurmaCommand command);
}
