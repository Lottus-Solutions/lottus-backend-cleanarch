package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    Boolean existsByNomeIgnoreCase(String nome);
    Integer countByCategoriaId(Long categoriaId);
    List<LivroEntity> findAll();
}
