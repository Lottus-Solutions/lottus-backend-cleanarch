package br.com.lottus.library.infrastructure.web.command;


import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterUserRequest (@NotNull String nome,
                                   @NotNull String email,
                                   String senha,
                                   Integer idAvatar){

    public CadastrarUsuarioCommand toCommand() {
        return new CadastrarUsuarioCommand(
                nome(),
                email(),
                senha(),
                idAvatar(),
                LocalDate.now()
        );
    }

}
