package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Turma;

import java.util.List;
import java.util.Optional;

public interface TurmaRepositoryPort {
    Turma save(Turma turma);
    Optional<Turma> findById(Long id);
    boolean existsByNomeIgnoreCase(String nome);
    List<Turma> findAll();
    void deleteById(Long id);
}
