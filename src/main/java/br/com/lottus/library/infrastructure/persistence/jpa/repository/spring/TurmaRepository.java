package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    Optional<TurmaEntity> findByNome(String nome);
}
