package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.application.ports.command.LoginCommand;

public record LoginResponse(String token) { }
