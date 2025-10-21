package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;

import java.util.Objects;

public class TurmaEntityMapper {

    public static TurmaEntity toEntity(Turma domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new TurmaEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        return entity;
    }

    public static Turma toDomain(TurmaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Turma.criarComId(
                entity.getId(),
                entity.getNome()
        );
    }
}
