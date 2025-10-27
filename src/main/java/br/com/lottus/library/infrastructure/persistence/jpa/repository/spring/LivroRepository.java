package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import br.com.lottus.library.infrastructure.web.dto.LivroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    Boolean existsByNomeIgnoreCase(String nome);
    Integer countByCategoriaId(Long categoriaId);
    List<LivroEntity> findAll();
}
