package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    List<TurmaEntity> findByNome(String nome);
}
