package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ResetarStatusEmprestimosUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;

import java.util.List;

public class ResetarStatusEmprestimosUseCaseImpl implements ResetarStatusEmprestimosUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;

    public ResetarStatusEmprestimosUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    public void executar() {
        List<Emprestimo> emprestimosFinalizados = emprestimoRepositoryPort.findByStatusEmprestimo(StatusEmprestimo.FINALIZADO);

        for (Emprestimo emprestimo : emprestimosFinalizados) {
            emprestimo.arquivar();
            emprestimoRepositoryPort.save(emprestimo);
        }
    }
}
