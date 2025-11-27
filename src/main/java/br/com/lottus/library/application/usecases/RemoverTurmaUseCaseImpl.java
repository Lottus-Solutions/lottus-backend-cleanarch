package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.in.RemoverTurmaUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import org.springframework.cache.annotation.CacheEvict;

public class RemoverTurmaUseCaseImpl implements RemoverTurmaUseCase {

    private final TurmaRepositoryPort turmaRepositoryPort;

    public RemoverTurmaUseCaseImpl(TurmaRepositoryPort turmaRepositoryPort) {
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    @CacheEvict(value = "turmas", allEntries = true)
    public void executar(Long id) {
        turmaRepositoryPort.findById(id).orElseThrow(TurmaNaoEncontradaException::new);
        turmaRepositoryPort.deleteById(id);
    }
}