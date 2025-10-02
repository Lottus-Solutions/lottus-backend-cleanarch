package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.CadastrarLivroCommand;
import br.com.lottus.library.domain.entities.Livro;

public interface CadastrarLivroUseCase {
    Livro executar(CadastrarLivroCommand command);
}
