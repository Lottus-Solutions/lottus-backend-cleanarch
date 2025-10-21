package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
