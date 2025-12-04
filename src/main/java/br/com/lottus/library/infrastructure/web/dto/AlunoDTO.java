package br.com.lottus.library.infrastructure.web.dto;

import java.io.Serializable;

public record AlunoDTO(
        Long matricula,
        String nome,
        Double qtdBonus,
        Long turmaId,
        Integer qtdLivrosLidos,
        String livroAtual
) implements Serializable {
}
