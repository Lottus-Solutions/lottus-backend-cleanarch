package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
}
