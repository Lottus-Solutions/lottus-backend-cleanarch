package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.BuscarAlunoPorMatriculaUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import org.springframework.cache.annotation.Cacheable;

public class BuscarAlunoPorMatriculaUseCaseImpl implements BuscarAlunoPorMatriculaUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public BuscarAlunoPorMatriculaUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    @Cacheable(value = "alunos", key = "#matricula")
    public Aluno executar(Long matricula) {
        return alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);
    }
}
