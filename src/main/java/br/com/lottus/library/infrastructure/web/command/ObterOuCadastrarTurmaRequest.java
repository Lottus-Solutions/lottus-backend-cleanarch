package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.application.ports.command.GerenciarTurmaCommand;

public record ObterOuCadastrarTurmaRequest(
        String nome
) {
    public GerenciarTurmaCommand toCommand() {
        return new GerenciarTurmaCommand(nome);
    };
}
