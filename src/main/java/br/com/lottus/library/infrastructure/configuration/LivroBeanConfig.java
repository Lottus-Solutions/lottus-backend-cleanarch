package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.usecases.CadastrarLivroImpl;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.CategoriaRepositoryAdapter;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.LivroRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivroBeanConfig {

    @Bean
    public CadastrarLivroImpl cadastrarLivroUseCase(LivroRepositoryAdapter livroPort, CategoriaRepositoryAdapter categoriaPort) {
        return new CadastrarLivroImpl(livroPort, categoriaPort);
    }

}
