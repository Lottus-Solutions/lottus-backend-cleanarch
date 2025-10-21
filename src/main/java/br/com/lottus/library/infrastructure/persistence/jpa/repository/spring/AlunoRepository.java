package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.AlunoEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    List<AlunoEntity> findAllByTurma(TurmaEntity turma);
    List<AlunoEntity> findAllByNomeContainingIgnoreCase(String nome);
    List<AlunoEntity> findByNomeContainingAndTurmaId(String nome, Long turmaId);
}
