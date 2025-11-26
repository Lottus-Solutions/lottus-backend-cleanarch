package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TurmaBeanConfig {

    @Bean
    public AdicionarTurmaUseCaseImpl adicionarTurmaUseCase(TurmaRepositoryPort turmaRepositoryPort) {
        return new AdicionarTurmaUseCaseImpl(turmaRepositoryPort);
    }

    @Bean
    public ListarTurmasUseCaseImpl listarTurmasUseCase(TurmaRepositoryPort turmaRepositoryPort) {
        return new ListarTurmasUseCaseImpl(turmaRepositoryPort);
    }

    @Bean
    public RemoverTurmaUseCaseImpl removerTurmaUseCase(TurmaRepositoryPort turmaRepositoryPort) {
        return new RemoverTurmaUseCaseImpl(turmaRepositoryPort);
    }

    @Bean
    public EditarTurmaUseCaseImpl editarTurmaUseCase(TurmaRepositoryPort turmaRepositoryPort) {
        return new EditarTurmaUseCaseImpl(turmaRepositoryPort);
    }

    @Bean
    public ObterOuCadastrarTurmaUseCaseImpl obterOuCadastrarTurmaUseCase(TurmaRepositoryPort turmaRepositoryPort) {
        return new ObterOuCadastrarTurmaUseCaseImpl(turmaRepositoryPort);
    }
}
