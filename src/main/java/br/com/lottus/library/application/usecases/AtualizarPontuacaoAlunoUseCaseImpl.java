package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.AtualizarPontuacaoAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;

public class AtualizarPontuacaoAlunoUseCaseImpl implements AtualizarPontuacaoAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public AtualizarPontuacaoAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public void executar(Long matricula) {
        Aluno aluno = alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);

        aluno.alterarQtdBonus(aluno.getQtdBonus() + 0.25);

        alunoRepositoryPort.save(aluno);
    }
}
