package br.com.lottus.library.application.ports.command;

public record CadastrarCategoriaCommand(
        String nome,
        String cor
) {
}
