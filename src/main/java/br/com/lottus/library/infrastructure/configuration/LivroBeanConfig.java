package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.in.RemoverLivroUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.application.usecases.AtualizarLivroUseCaseImpl;
import br.com.lottus.library.application.usecases.CadastrarLivroImpl;
import br.com.lottus.library.application.usecases.ListarLivrosUseCaseImpl;
import br.com.lottus.library.application.usecases.RemoverLivroUseCaseImpl;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.CategoriaRepositoryAdapter;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.LivroRepositoryAdapter;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.CategoriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivroBeanConfig {

    @Bean
    public CadastrarLivroImpl cadastrarLivroUseCase(LivroRepositoryAdapter livroPort, CategoriaRepositoryAdapter categoriaPort) {
        return new CadastrarLivroImpl(livroPort, categoriaPort);
    }

    @Bean
    public ListarLivrosUseCaseImpl listarLivrosUseCase(LivroRepositoryAdapter livroPort) {
        return new ListarLivrosUseCaseImpl(livroPort);
    }

    @Bean
    public RemoverLivroUseCaseImpl removerLivroUseCase(LivroRepositoryAdapter livroPort) {
        return new RemoverLivroUseCaseImpl(livroPort);
    }

    @Bean
    public AtualizarLivroUseCaseImpl atualizarLivroUseCase(LivroRepositoryAdapter livroPort, CategoriaRepositoryAdapter categoriaPort) {
        return new AtualizarLivroUseCaseImpl(livroPort, categoriaPort);
    }

}
