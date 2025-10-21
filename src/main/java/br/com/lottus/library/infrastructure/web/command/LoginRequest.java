package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.application.ports.command.LoginCommand;

public record LoginRequest(String email, String senha) {
    public LoginCommand toCommand(){
        return new LoginCommand(email(), senha());
    }

}
