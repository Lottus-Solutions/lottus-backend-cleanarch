package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ListarAlunosPorTurmaUseCase {
    Page<Aluno> executar(Long turmaId, Pageable pageable);
}
