package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.CategoriaEntity;

import java.util.Objects;

public class CategoriaEntityMapper {
    public static CategoriaEntity toEntity(Categoria domain) {
        if (Objects.isNull(domain))
            return null;

        var entity = new CategoriaEntity();
        entity.setNome(domain.getNome());
        entity.setCor(domain.getCor());

        return entity;
    }

    public static Categoria toDomain(CategoriaEntity entity) {
        if (Objects.isNull(entity))
            return null;

        return Categoria.criarComId(
                entity.getId(),
                entity.getNome(),
                entity.getCor()
        );
    }
}
