package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Usuario;

public interface BuscarUsuarioLogadoUseCase {
    Usuario execute(String email);
}