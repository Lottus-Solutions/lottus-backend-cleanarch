package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.NenhumAlunoEncontradoException;
import br.com.lottus.library.application.ports.in.ListarAlunosUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class ListarAlunosUseCaseImpl implements ListarAlunosUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public ListarAlunosUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    @Cacheable("alunos")
    public List<Aluno> executar() {
        List<Aluno> alunos = alunoRepositoryPort.findAll();
        if (alunos.isEmpty()) {
            throw new NenhumAlunoEncontradoException();
        }
        return alunos;
    }
}
