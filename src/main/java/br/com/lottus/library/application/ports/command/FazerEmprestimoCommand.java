package br.com.lottus.library.application.ports.command;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FazerEmprestimoCommand(
        @NotNull(message = "A matrícula do aluno não pode ser nula.")
        Long matriculaAluno,

        @NotNull(message = "O ID do livro não pode ser nulo.")
        Long fk_livro,

        @NotNull(message = "A data do empréstimo não pode ser nula.")
        LocalDate dataEmprestimo
) {
}
