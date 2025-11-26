package br.com.lottus.library.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        String nomeAluno,
        String turmaAluno,
        String nomeLivro,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataDevolucaoPrevista,
        int diasAtrasados
) {
}
