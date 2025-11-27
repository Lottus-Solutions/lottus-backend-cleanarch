package br.com.lottus.library.infrastructure.web.dto;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;

import java.io.Serializable;

public record LivroResponseDTO(Long id, String nome, String autor, Integer quantidade, Integer quantidadeDisponivel, StatusLivro status, String categoria, String descricao) implements Serializable {
    public static LivroResponseDTO fromDomain(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getQuantidade(),
                livro.getQuantidadeDisponivel(),
                livro.getStatus(),
                livro.getCategoria() != null ? livro.getCategoria().getNome() : null,
                livro.getDescricao()
        );
    }
}
