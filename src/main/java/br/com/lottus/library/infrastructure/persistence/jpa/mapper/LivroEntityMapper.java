package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.CategoriaEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LivroEntityMapper  {

    public static LivroEntity toEntity(Livro domain, CategoriaEntity categoriaEntity) {
        if (Objects.isNull(domain))
            return null;

        var entity = new LivroEntity();
        entity.setNome(domain.getNome());
        entity.setAutor(domain.getAutor());
        entity.setQuantidade(domain.getQuantidade());
        entity.setQuantidadeDisponivel(domain.getQuantidadeDisponivel());
        entity.setStatus(domain.getStatus());
        entity.setDescricao(domain.getDescricao());
        entity.setCategoria(categoriaEntity);

        return entity;
    }

    public static Livro toDomain(LivroEntity entity) {
        if (Objects.isNull(entity))
            return null;

        return Livro.criarComId(
                entity.getId(),
                entity.getNome(),
                entity.getAutor(),
                CategoriaEntityMapper.toDomain(entity.getCategoria()),
                entity.getQuantidade(),
                entity.getQuantidadeDisponivel(),
                entity.getStatus(),
                entity.getDescricao()
        );
    }
}
