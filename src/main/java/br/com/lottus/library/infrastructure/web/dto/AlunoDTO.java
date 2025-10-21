package br.com.lottus.library.infrastructure.web.dto;

public record AlunoDTO(
        Long matricula,
        String nome,
        Double qtdBonus,
        Long turmaId,
        Integer qtdLivrosLidos,
        String livroAtual
) {
}
