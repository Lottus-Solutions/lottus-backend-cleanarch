package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    boolean existsByNome(String nome);
    Categoria save(Categoria domain);
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
    void deleteById(Long id);
}
