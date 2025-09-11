package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CredenciaisInvalidasExceptions;
import br.com.lottus.library.application.exceptions.UsuarioNaoEncontradoException;
import br.com.lottus.library.application.ports.command.LoginCommand;
import br.com.lottus.library.application.ports.in.LoginUseCase;
import br.com.lottus.library.application.ports.out.JwtProviderPort;
import br.com.lottus.library.application.ports.out.PasswordEncoderPort;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;

public class LoginUseCaseImpl implements LoginUseCase {
    private final UsuarioRepositoryPort port;
    private final PasswordEncoderPort encoderPort;
    private final JwtProviderPort jwtProvider;

    public LoginUseCaseImpl(UsuarioRepositoryPort port, PasswordEncoderPort encoderPort, JwtProviderPort jwtProvider) {
        this.port = port;
        this.encoderPort = encoderPort;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String execute(LoginCommand command) {
        Usuario usuario = port.findByEmail(command.email())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if(!encoderPort.matches(command.senha(), usuario.getSenha())) {
            throw new CredenciaisInvalidasExceptions();
        }
        return jwtProvider.generateToken(command.email());
    }
}
