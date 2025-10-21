package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.command.EditarAlunoCommand;
import br.com.lottus.library.application.ports.in.EditarAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;

public class EditarAlunoUseCaseImpl implements EditarAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;
    private final TurmaRepositoryPort turmaRepositoryPort;

    public EditarAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public Aluno executar(Long matricula, EditarAlunoCommand command) {
        Aluno aluno = alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);

        if (command.nome() != null) {
            aluno.alterarNome(command.nome());
        }
        if (command.qtdBonus() != null) {
            aluno.alterarQtdBonus(command.qtdBonus());
        }
        if (command.qtdLivrosLidos() != null) {
            aluno.alterarQtdLivrosLidos(command.qtdLivrosLidos());
        }
        if (command.turmaId() != null) {
            Turma turma = turmaRepositoryPort.findById(command.turmaId())
                    .orElseThrow(TurmaNaoEncontradaException::new);
            aluno.alterarTurma(turma);
        }

        return alunoRepositoryPort.save(aluno);
    }
}
