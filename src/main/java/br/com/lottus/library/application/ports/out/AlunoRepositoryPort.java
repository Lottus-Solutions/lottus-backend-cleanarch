package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepositoryPort {
    Aluno save(Aluno aluno);
    List<Aluno> findAll();
    Optional<Aluno> findById(Long id);
    void deleteById(Long id);
    List<Aluno> findAllByTurma(br.com.lottus.library.domain.entities.Turma turma);
    List<Aluno> findAllByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByNomeContainingAndTurmaId(String nome, Long turmaId);
}
