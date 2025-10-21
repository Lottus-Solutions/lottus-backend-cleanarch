package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.EditarAlunoCommand;
import br.com.lottus.library.domain.entities.Aluno;

public interface EditarAlunoUseCase {
    Aluno executar(Long matricula, EditarAlunoCommand command);
}
