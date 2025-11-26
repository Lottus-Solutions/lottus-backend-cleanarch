package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.web.command.AtualizarLivroCommand;

public interface AtualizarLivroUseCase {
    Livro executar(Long id, AtualizarLivroCommand command);
}
