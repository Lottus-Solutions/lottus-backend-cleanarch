package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.RemoverAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;

public class RemoverAlunoUseCaseImpl implements RemoverAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public RemoverAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public void executar(Long matricula) {
        alunoRepositoryPort.findById(matricula).orElseThrow(AlunoNaoEncontradoException::new);
        alunoRepositoryPort.deleteById(matricula);
    }
}
