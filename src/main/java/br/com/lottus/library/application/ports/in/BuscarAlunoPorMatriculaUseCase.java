package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Aluno;

public interface BuscarAlunoPorMatriculaUseCase {
    Aluno executar(Long matricula);
}
