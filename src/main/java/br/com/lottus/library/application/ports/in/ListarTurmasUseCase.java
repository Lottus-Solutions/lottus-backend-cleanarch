package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Turma;

import java.util.List;

public interface ListarTurmasUseCase {
    List<Turma> executar();
}
