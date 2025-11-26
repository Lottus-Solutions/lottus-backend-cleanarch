package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.FiltrarEmprestimosAtrasadosUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;

import java.util.List;

public class FiltrarEmprestimosAtrasadosUseCaseImpl implements FiltrarEmprestimosAtrasadosUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;

    public FiltrarEmprestimosAtrasadosUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    public List<Emprestimo> executar() {
        return emprestimoRepositoryPort.findByStatusEmprestimo(StatusEmprestimo.ATRASADO);
    }
}
