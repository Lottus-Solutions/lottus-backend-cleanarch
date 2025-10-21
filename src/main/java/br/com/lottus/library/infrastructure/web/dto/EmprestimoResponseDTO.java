package br.com.lottus.library.infrastructure.web.dto;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        String alunoNome,
        String turmaNome,
        String livroNome,
        LocalDate dataDevolucaoPrevista,
        int diasAtrasados
) {
}
