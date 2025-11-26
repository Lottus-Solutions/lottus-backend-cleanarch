package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlunoBeanConfig {

    @Bean
    public AdicionarAlunoUseCaseImpl adicionarAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        return new AdicionarAlunoUseCaseImpl(alunoRepositoryPort, turmaRepositoryPort);
    }

    @Bean
    public RemoverAlunoUseCaseImpl removerAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new RemoverAlunoUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public EditarAlunoUseCaseImpl editarAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        return new EditarAlunoUseCaseImpl(alunoRepositoryPort, turmaRepositoryPort);
    }

    @Bean
    public ListarAlunosPorTurmaUseCaseImpl listarAlunosPorTurmaUseCase(AlunoRepositoryPort alunoRepositoryPort, TurmaRepositoryPort turmaRepositoryPort) {
        return new ListarAlunosPorTurmaUseCaseImpl(alunoRepositoryPort, turmaRepositoryPort);
    }

    @Bean
    public BuscarAlunoPorMatriculaUseCaseImpl buscarAlunoPorMatriculaUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new BuscarAlunoPorMatriculaUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public ListarAlunosUseCaseImpl listarAlunosUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new ListarAlunosUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public AtualizarPontuacaoAlunoUseCaseImpl atualizarPontuacaoAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new AtualizarPontuacaoAlunoUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public AtualizarLivrosLidosAlunoUseCaseImpl atualizarLivrosLidosAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new AtualizarLivrosLidosAlunoUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public ConstruirPerfilAlunoUseCaseImpl construirPerfilAlunoUseCase(AlunoRepositoryPort alunoRepositoryPort, EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new ConstruirPerfilAlunoUseCaseImpl(alunoRepositoryPort, emprestimoRepositoryPort);
    }

    @Bean
    public ResetarBonusAlunosUseCaseImpl resetarBonusAlunosUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new ResetarBonusAlunosUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public ResetarLivrosLidosAlunosUseCaseImpl resetarLivrosLidosAlunosUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new ResetarLivrosLidosAlunosUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public ListarAlunosPorNomeUseCaseImpl listarAlunosPorNomeUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new ListarAlunosPorNomeUseCaseImpl(alunoRepositoryPort);
    }

    @Bean
    public BuscarAlunosPorNomeETurmaUseCaseImpl buscarAlunosPorNomeETurmaUseCase(AlunoRepositoryPort alunoRepositoryPort) {
        return new BuscarAlunosPorNomeETurmaUseCaseImpl(alunoRepositoryPort);
    }
}
