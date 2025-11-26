package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Aluno;

import java.util.List;

public interface ListarAlunosUseCase {
    List<Aluno> executar();
}
