package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.NenhumAlunoEncontradoException;
import br.com.lottus.library.application.ports.in.ListarAlunosPorNomeUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class ListarAlunosPorNomeUseCaseImpl implements ListarAlunosPorNomeUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public ListarAlunosPorNomeUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    @Cacheable(value = "alunos", key = "#nome")
    public List<Aluno> executar(String nome) {
        List<Aluno> alunos = alunoRepositoryPort.findAllByNomeContainingIgnoreCase(nome);
        if (alunos.isEmpty()) {
            throw new NenhumAlunoEncontradoException();
        }
        return alunos;
    }
}
