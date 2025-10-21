package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.AdicionarAlunoCommand;
import br.com.lottus.library.domain.entities.Aluno;

public interface AdicionarAlunoUseCase {
    Aluno executar(AdicionarAlunoCommand command);
}
