package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CredenciaisInvalidasExceptions;
import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;
import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.out.PasswordEncoderPort;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.domain.validators.UsuarioValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    private static final Logger log = LoggerFactory.getLogger(CadastrarUsuarioUseCaseImpl.class);

    private final UsuarioRepositoryPort port;
    private final PasswordEncoderPort encoderPort;

    public CadastrarUsuarioUseCaseImpl(UsuarioRepositoryPort port, PasswordEncoderPort encoderPort) {
        this.port = port;
        this.encoderPort = encoderPort;
    }

    @Override
    public Usuario executar(CadastrarUsuarioCommand command) {
            UsuarioValidator.validarCampos(command);

        if (port.findByEmail(command.email()).isPresent()) {
            log.warn("Tentativa de cadastro com e-mail já existente: {}", command.email());
            throw new UsuarioJaCadastradoComEmailException("E-mail já cadastrado.");
        }

        Usuario novoUsuario = Usuario.criar(command);
        novoUsuario.setSenha(encoderPort.encode(command.senha()));

        return port.save(novoUsuario);
    }
}
