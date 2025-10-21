package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Emprestimo;

import java.util.List;

public interface BuscarHistoricoLivroUseCase {
    List<Emprestimo> executar(Long livroId);
}
