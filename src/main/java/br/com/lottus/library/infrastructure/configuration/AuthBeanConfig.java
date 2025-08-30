package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.application.usecases.CadastrarUsuarioUseCaseImpl;
import br.com.lottus.library.infrastructure.persistence.repository.UsuarioRepositoryAdaptar;
import br.com.lottus.library.infrastructure.security.PasswordEncoderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfig {

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(UsuarioRepositoryAdaptar adapter, PasswordEncoderAdapter encoderAdapter) {
        return new CadastrarUsuarioUseCaseImpl(adapter, encoderAdapter);
    }
}