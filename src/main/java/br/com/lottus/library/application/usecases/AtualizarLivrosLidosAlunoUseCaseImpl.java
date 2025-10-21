package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.AtualizarLivrosLidosAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;

public class AtualizarLivrosLidosAlunoUseCaseImpl implements AtualizarLivrosLidosAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;

    public AtualizarLivrosLidosAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
    }

    @Override
    public void executar(Long matricula) {
        Aluno aluno = alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);

        aluno.alterarQtdLivrosLidos(aluno.getQtdLivrosLidos() + 1);

        alunoRepositoryPort.save(aluno);
    }
}
