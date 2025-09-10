package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.LoginCommand;

public interface LoginUseCase {
    String execute(LoginCommand command);
}
