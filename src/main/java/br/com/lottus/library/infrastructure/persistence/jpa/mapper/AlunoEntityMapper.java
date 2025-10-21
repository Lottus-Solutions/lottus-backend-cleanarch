package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.AlunoEntity;

import java.util.Objects;
import java.util.stream.Collectors;

public class AlunoEntityMapper {

    public static AlunoEntity toEntity(Aluno domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new AlunoEntity();
        entity.setMatricula(domain.getMatricula());
        entity.setNome(domain.getNome());
        entity.setQtdBonus(domain.getQtdBonus());
        entity.setQtdLivrosLidos(domain.getQtdLivrosLidos());
        entity.setTurma(TurmaEntityMapper.toEntity(domain.getTurma()));
        // Emprestimos are not mapped here to avoid circular dependencies when converting to entity

        return entity;
    }

    public static Aluno toDomain(AlunoEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        Aluno aluno = toDomainSimple(entity);
        List<br.com.lottus.library.domain.entities.Emprestimo> emprestimos = entity.getEmprestimos().stream()
                .map(emprestimoEntity -> EmprestimoEntityMapper.toDomain(emprestimoEntity, aluno))
                .collect(Collectors.toList());

        return Aluno.criarComId(
                entity.getMatricula(),
                entity.getNome(),
                TurmaEntityMapper.toDomain(entity.getTurma()),
                entity.getQtdBonus(),
                entity.getQtdLivrosLidos(),
                emprestimos
        );
    }

    public static Aluno toDomainSimple(AlunoEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Aluno.criarComId(
                entity.getMatricula(),
                entity.getNome(),
                TurmaEntityMapper.toDomain(entity.getTurma()),
                entity.getQtdBonus(),
                entity.getQtdLivrosLidos(),
                new java.util.ArrayList<>()
        );
    }
}
