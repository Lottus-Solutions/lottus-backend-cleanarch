package br.com.lottus.library.infrastructure.web.command;

import br.com.lottus.library.domain.entities.StatusLivro;

public record AtualizarLivroCommand(
        String nome,
        String autor,
        Integer quantidade,
        Integer quantidadeDisponivel,
        StatusLivro status,
        String descricao,
        Long categoriaId
) {
}
