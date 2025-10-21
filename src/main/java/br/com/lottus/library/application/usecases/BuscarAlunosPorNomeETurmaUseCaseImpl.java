package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.BuscarAlunosPorNomeETurmaUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;

import java.util.List;

public class BuscarAlunosPorNomeETurmaUseCaseImpl implements BuscarAlunosPorNomeETurmaUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public BuscarAlunosPorNomeETurmaUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public List<Aluno> executar(String nome, Long turmaId) {
        return alunoRepositoryPort.findByNomeContainingAndTurmaId(nome, turmaId);
    }
}
