package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;

public record ObterOuCriarCategoriaRequest(
    String nome,
    String cor
) {
    public CadastrarCategoriaCommand toCommand() {
        return new CadastrarCategoriaCommand(nome, cor);
    }
}
