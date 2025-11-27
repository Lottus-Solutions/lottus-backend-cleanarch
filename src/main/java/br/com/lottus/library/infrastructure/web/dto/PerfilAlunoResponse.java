package br.com.lottus.library.infrastructure.web.dto;

import br.com.lottus.library.domain.entities.Livro;

import java.io.Serializable;
import java.time.LocalDate;

public record PerfilAlunoResponse(
        AlunoDTO aluno,
        boolean temAtraso,
        Livro livroEmprestado,
        LocalDate dataDevolucaoPrevista
) implements Serializable {
}
