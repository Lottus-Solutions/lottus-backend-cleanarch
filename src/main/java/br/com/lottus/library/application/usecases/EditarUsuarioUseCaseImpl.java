package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.UsuarioNaoEncontradoException;
import br.com.lottus.library.application.ports.command.EditarUsuarioCommand;
import br.com.lottus.library.application.ports.in.EditarUsuarioUseCase;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;

public class EditarUsuarioUseCaseImpl implements EditarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public EditarUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario execute(EditarUsuarioCommand command) {
        Usuario usuario = usuarioRepositoryPort.findById(command.id())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com o ID: " + command.id()));

        usuario.atualizarPerfil(command.nome(), command.idAvatar());

        return usuarioRepositoryPort.save(usuario);
    }
}
