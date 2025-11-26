package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlunoRepositoryPort {
    Aluno save(Aluno aluno);
    List<Aluno> findAll();
    Optional<Aluno> findById(Long id);
    void deleteById(Long id);
    Page<Aluno> findAllByTurma(Turma turma, Pageable pageable);
    List<Aluno> findAllByNomeContainingIgnoreCase(String nome);
    List<Aluno> findByNomeContainingAndTurmaId(String nome, Long turmaId);
}
