package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.domain.entities.Usuario;

public record EditarUsuarioResponse(
        String nome,
        String email
) {
    public static EditarUsuarioResponse of(Usuario usuario) {
        return new EditarUsuarioResponse(
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
