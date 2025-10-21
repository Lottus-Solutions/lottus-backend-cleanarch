package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.EmprestimoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.VerificarQuantidadeRenovadoUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.infrastructure.web.dto.VerificarRenovadoResponse;

public class VerificarQuantidadeRenovadoUseCaseImpl implements VerificarQuantidadeRenovadoUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;

    public VerificarQuantidadeRenovadoUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    public VerificarRenovadoResponse executar(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepositoryPort.findById(emprestimoId)
                .orElseThrow(EmprestimoNaoEncontradoException::new);

        int qtdRenovado = emprestimo.getQtdRenovado();
        return new VerificarRenovadoResponse(qtdRenovado > 0, qtdRenovado);
    }
}
