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
                    try {
                        Turma turmaNova = Turma.criar(command.nome());
                        return turmaRepository.save(turmaNova);
                    } catch (Exception e) {
                        // Se falhar (ex: condição de corrida criando duplicata), tenta buscar novamente
                        return turmaRepository.findByNome(command.nome())
                                .orElseThrow(() -> new RuntimeException("Erro ao obter ou criar turma: " + command.nome(), e));
                    }
                });
    }
}
