package br.com.lottus.library.application.ports.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdicionarAlunoCommand(
        Long matricula,
        @NotBlank(message = "O nome do aluno não pode ser vazio.")
        String nome,
        Double qtdBonus,
        @NotNull(message = "O ID da turma não pode ser nulo.")
        Long turmaId,
        Integer qtdLivrosLidos,
        String livroAtual
) {
}
