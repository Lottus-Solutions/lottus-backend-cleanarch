package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.EmprestimoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.RenovarEmprestimoUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import org.springframework.cache.annotation.CacheEvict;

public class RenovarEmprestimoUseCaseImpl implements RenovarEmprestimoUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private static final int DIAS_ADICIONAIS_RENOVACAO = 15;

    public RenovarEmprestimoUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    @CacheEvict(value = "emprestimos", allEntries = true)
    public void executar(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepositoryPort.findById(emprestimoId)
                .orElseThrow(EmprestimoNaoEncontradoException::new);

        emprestimo.renovar(DIAS_ADICIONAIS_RENOVACAO);

        emprestimoRepositoryPort.save(emprestimo);
    }
}
