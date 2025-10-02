package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;

import java.util.Objects;

public class LivroEntityMapper  {
    public static LivroEntity toEntity(Livro domain) {
        if (Objects.isNull(domain))
            return null;

        var entity = new LivroEntity();
        entity.setNome(domain.getNome());
        entity.setAutor(domain.getAutor());
        entity.setQuantidade(domain.getQuantidade());
        entity.setQuantidadeDisponivel(domain.getQuantidadeDisponivel());
        entity.setStatus(domain.getStatus());
        entity.setDescricao(domain.getDescricao());
        entity.setCategoria(domain.getCategoria());

        return entity;
    }

    public static Livro toDomain(LivroEntity entity) {
        if (Objects.isNull(entity))
            return null;

        return Livro.criarComId(
                entity.getId(),
                entity.getNome(),
                entity.getAutor(),
                entity.getCategoria(),
                entity.getQuantidade(),
                entity.getQuantidadeDisponivel(),
                entity.getStatus(),
                entity.getDescricao()
        );
    }
}
