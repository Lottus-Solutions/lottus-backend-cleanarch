package br.com.lottus.library.application.ports.command;

import jakarta.validation.constraints.NotBlank;

public record GerenciarTurmaCommand(
        @NotBlank(message = "O nome da turma não pode ser vazio.")
        String nome
) {
}
