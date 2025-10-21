package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.FazerEmprestimoCommand;
import br.com.lottus.library.domain.entities.Emprestimo;

public interface FazerEmprestimoUseCase {
    Emprestimo executar(FazerEmprestimoCommand command);
}
