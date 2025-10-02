package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepositoryPort {
    Livro save(Livro domain);
    List<Livro> findAll();
    Optional<Livro> findById(Long id);
    void deleteById(Long id);
    Integer countByCategoriaId(Long categoriaId);
    Boolean existsByNomeIgnoreCase(String nome);
}
