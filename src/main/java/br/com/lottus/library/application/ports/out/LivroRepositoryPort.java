package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;
import br.com.lottus.library.infrastructure.web.dto.LivroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepositoryPort {
    Page<LivroResponseDTO> buscarLivros(String termoBusca, List<StatusLivro> status, Long categoriaId, Pageable pageable);
    Livro save(Livro domain);
    List<Livro> findAll();
    Optional<Livro> findById(Long id);
    void deleteById(Long id);
    Integer countByCategoriaId(Long categoriaId);
    Boolean existsByNomeIgnoreCase(String nome);
}
