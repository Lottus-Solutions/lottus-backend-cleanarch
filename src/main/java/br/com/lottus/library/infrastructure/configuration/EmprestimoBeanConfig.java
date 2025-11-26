package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmprestimoBeanConfig {

    @Bean
    public FazerEmprestimoUseCaseImpl fazerEmprestimoUseCase(AlunoRepositoryPort alunoRepositoryPort, LivroRepositoryPort livroRepositoryPort, EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new FazerEmprestimoUseCaseImpl(alunoRepositoryPort, livroRepositoryPort, emprestimoRepositoryPort);
    }

    @Bean
    public FinalizarEmprestimoUseCaseImpl finalizarEmprestimoUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort, AlunoRepositoryPort alunoRepositoryPort, LivroRepositoryPort livroRepositoryPort) {
        return new FinalizarEmprestimoUseCaseImpl(emprestimoRepositoryPort, alunoRepositoryPort, livroRepositoryPort);
    }

    @Bean
    public RenovarEmprestimoUseCaseImpl renovarEmprestimoUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new RenovarEmprestimoUseCaseImpl(emprestimoRepositoryPort);
    }

    @Bean
    public ListarEmprestimosUseCaseImpl listarEmprestimosUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new ListarEmprestimosUseCaseImpl(emprestimoRepositoryPort);
    }

    @Bean
    public BuscarHistoricoLivroUseCaseImpl buscarHistoricoLivroUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort, LivroRepositoryPort livroRepositoryPort) {
        return new BuscarHistoricoLivroUseCaseImpl(emprestimoRepositoryPort, livroRepositoryPort);
    }

    @Bean
    public BuscarHistoricoAlunoUseCaseImpl buscarHistoricoAlunoUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort, AlunoRepositoryPort alunoRepositoryPort) {
        return new BuscarHistoricoAlunoUseCaseImpl(emprestimoRepositoryPort, alunoRepositoryPort);
    }

    @Bean
    public FiltrarEmprestimosAtrasadosUseCaseImpl filtrarEmprestimosAtrasadosUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new FiltrarEmprestimosAtrasadosUseCaseImpl(emprestimoRepositoryPort);
    }

    @Bean
    public VerificarQuantidadeRenovadoUseCaseImpl verificarQuantidadeRenovadoUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new VerificarQuantidadeRenovadoUseCaseImpl(emprestimoRepositoryPort);
    }

    @Bean
    public ResetarStatusEmprestimosUseCaseImpl resetarStatusEmprestimosUseCase(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        return new ResetarStatusEmprestimosUseCaseImpl(emprestimoRepositoryPort);
    }
}
