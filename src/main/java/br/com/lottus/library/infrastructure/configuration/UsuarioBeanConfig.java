package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.in.BuscarUsuarioLogadoUseCase;
import br.com.lottus.library.application.ports.in.EditarUsuarioUseCase;
import br.com.lottus.library.application.usecases.BuscarUsuarioLogadoUseCaseImpl;
import br.com.lottus.library.application.usecases.EditarUsuarioUseCaseImpl;
import br.com.lottus.library.infrastructure.persistence.repository.UsuarioRepositoryAdaptar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioBeanConfig {

    @Bean
    public BuscarUsuarioLogadoUseCase buscarUsuarioLogadoUseCase(UsuarioRepositoryAdaptar adapter) {
        return new BuscarUsuarioLogadoUseCaseImpl(adapter);
    }

    @Bean
    public EditarUsuarioUseCase editarUsuarioUseCase(UsuarioRepositoryAdaptar adapter) {
        return new EditarUsuarioUseCaseImpl(adapter);
    }
}