package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.command.AdicionarAlunoCommand;
import br.com.lottus.library.application.ports.in.AdicionarAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;

public class AdicionarAlunoUseCaseImpl implements AdicionarAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;
    private final TurmaRepositoryPort turmaRepositoryPort;

    public AdicionarAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.turmaRepositoryPort = turmaRepositoryPort;
    }

    @Override
    public Aluno executar(AdicionarAlunoCommand command) {
        Turma turma = turmaRepositoryPort.findById(command.turmaId())
                .orElseThrow(TurmaNaoEncontradaException::new);

        Aluno aluno = Aluno.criar(command.nome(), turma);

        return alunoRepositoryPort.save(aluno);
    }
}
