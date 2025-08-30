package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CredenciaisInvalidasExceptions;
import br.com.lottus.library.application.exceptions.UsuarioNaoEncontradoException;
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
    public String execute(String email, String senha) {
        Usuario usuario = port.findByEmail(email)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        if(!encoderPort.matches(senha, usuario.getSenha())) {
            throw new CredenciaisInvalidasExceptions();
        }
        return jwtProvider.generateToken(email);
    }
}
