package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.NenhumAlunoEncontradoException;
import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.in.ListarAlunosPorTurmaUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ListarAlunosPorTurmaUseCaseImpl implements ListarAlunosPorTurmaUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;
    private final TurmaRepositoryPort turmaRepositoryPort;

    public ListarAlunosPorTurmaUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public Page<Aluno> executar(Long turmaId, Pageable pageable) {
        Turma turma = turmaRepositoryPort.findById(turmaId)
                .orElseThrow(TurmaNaoEncontradaException::new);

        Page<Aluno> alunos = alunoRepositoryPort.findAllByTurma(turma, pageable);

        if (alunos.isEmpty()) {
            throw new NenhumAlunoEncontradoException();
        }

        return alunos;
    }
}
