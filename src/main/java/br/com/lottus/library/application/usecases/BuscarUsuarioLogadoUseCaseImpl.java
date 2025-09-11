package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.UsuarioNaoEncontradoException;
import br.com.lottus.library.application.ports.in.BuscarUsuarioLogadoUseCase;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;

public class BuscarUsuarioLogadoUseCaseImpl implements BuscarUsuarioLogadoUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public BuscarUsuarioLogadoUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario execute(String email) {
        return usuarioRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com o email: " + email));
    }
}