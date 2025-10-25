package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;
import br.com.lottus.library.application.ports.in.ObterOuCadastrarTurmaUseCase;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;

public class ObterOuCadastrarTurmaUseCaseImpl implements ObterOuCadastrarTurmaUseCase {

    private final TurmaRepositoryPort turmaRepository;

    public ObterOuCadastrarTurmaUseCaseImpl(TurmaRepositoryPort turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    @Override
    public Turma executar(GerenciarTurmaCommand command) {
       return turmaRepository.findByNome(command.nome())
               .orElseGet(() -> {
                   Turma turmaNova = Turma.criar(command.nome());
                   return turmaRepository.save(turmaNova);
               });
    }
}
