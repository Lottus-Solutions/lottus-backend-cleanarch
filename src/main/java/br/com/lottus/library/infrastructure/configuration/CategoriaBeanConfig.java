package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.usecases.CadastrarCategoriaImpl;
import br.com.lottus.library.application.usecases.ListarCategoriaImpl;
import br.com.lottus.library.application.usecases.ObterOuCriarCategoriaUseCaseImpl;
import br.com.lottus.library.application.usecases.RemoverCategoriaUseCaseImpl;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.CategoriaRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaBeanConfig {

    @Bean
    public CadastrarCategoriaImpl cadastrarCategoriaUseCase(CategoriaRepositoryAdapter port) {
        return new CadastrarCategoriaImpl(port);
    }

    @Bean
    public ListarCategoriaImpl listarCategoriaUseCase(CategoriaRepositoryAdapter port) {
        return new ListarCategoriaImpl(port);
    }

    @Bean
    public RemoverCategoriaUseCaseImpl removerCategoriaUseCase(CategoriaRepositoryAdapter port) {
        return new RemoverCategoriaUseCaseImpl(port);
    }

    @Bean
    public ObterOuCriarCategoriaUseCaseImpl obterOuCriarCategoriaUseCase(CategoriaRepositoryAdapter port) {
        return new ObterOuCriarCategoriaUseCaseImpl(port);
    }

}
