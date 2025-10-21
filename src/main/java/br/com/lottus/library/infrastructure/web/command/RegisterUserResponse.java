package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.domain.entities.Usuario;

public record RegisterUserResponse (
        Long id,
        String email,
        String name
){
    public static RegisterUserResponse of(Usuario usuario) {
        return new RegisterUserResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }


}
