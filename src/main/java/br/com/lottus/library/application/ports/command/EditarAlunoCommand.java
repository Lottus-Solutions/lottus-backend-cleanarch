package br.com.lottus.library.application.ports.command;

public record EditarAlunoCommand(
        Long matricula,
        String nome,
        Double qtdBonus,
        Long turmaId,
        Integer qtdLivrosLidos,
        String livroAtual
) {
}
