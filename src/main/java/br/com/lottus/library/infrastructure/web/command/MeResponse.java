package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.domain.entities.Usuario;

import java.time.LocalDate;

public record MeResponse(
        Long id,
        String nome,
        String email,
        LocalDate dataRegistro,
        int idAvatar
) {
    public static MeResponse of(Usuario usuario) {
        return new MeResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataRegistro(),
                usuario.getIdAvatar()
        );
    }
}
