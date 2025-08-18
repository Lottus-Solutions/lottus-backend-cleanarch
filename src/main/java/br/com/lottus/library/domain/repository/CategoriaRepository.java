package br.com.lottus.library.domain.repository;

import br.com.lottus.library.domain.entities.Categoria;

import java.util.List;

public interface CategoriaRepository {

    Categoria findById(Long id);
    List<Categoria> findAll();
    Categoria save(Categoria categoria);
    void deleteById(Long id);
    boolean existsByNome(String nome);
    int countByCategoriaId(Long id);

}
