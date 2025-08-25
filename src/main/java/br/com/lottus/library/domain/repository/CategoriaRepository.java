package br.com.lottus.library.domain.repository;

import br.com.lottus.library.domain.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
    Categoria save(Categoria categoria);
    void deleteById(Long id);
    boolean existsByNome(String nome);

}
