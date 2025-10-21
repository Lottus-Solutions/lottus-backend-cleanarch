package br.com.lottus.library.infrastructure.persistence.jpa.mapper;

import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.EmprestimoEntity;

import java.util.Objects;

public class EmprestimoEntityMapper {

    public static EmprestimoEntity toEntity(Emprestimo domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        var entity = new EmprestimoEntity();
        entity.setId(domain.getId());
        entity.setAluno(AlunoEntityMapper.toEntity(domain.getAluno()));
        entity.setLivro(LivroEntityMapper.toEntity(domain.getLivro(), domain.getLivro().getCategoria() != null ? CategoriaEntityMapper.toEntity(domain.getLivro().getCategoria()) : null));
        entity.setDataEmprestimo(domain.getDataEmprestimo());
        entity.setDataDevolucaoPrevista(domain.getDataDevolucaoPrevista());
        entity.setDiasAtrasados(domain.getDiasAtrasados());
        entity.setQtdRenovado(domain.getQtdRenovado());
        entity.setStatusEmprestimo(domain.getStatusEmprestimo());

        return entity;
    }

    public static Emprestimo toDomain(EmprestimoEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Emprestimo.criarComId(
                entity.getId(),
                AlunoEntityMapper.toDomainSimple(entity.getAluno()),
                LivroEntityMapper.toDomain(entity.getLivro()),
                entity.getDataEmprestimo(),
                entity.getDataDevolucaoPrevista(),
                entity.getDiasAtrasados(),
                entity.getQtdRenovado(),
                entity.getStatusEmprestimo()
        );
    }

    public static Emprestimo toDomain(EmprestimoEntity entity, Aluno aluno) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Emprestimo.criarComId(
                entity.getId(),
                aluno,
                LivroEntityMapper.toDomain(entity.getLivro()),
                entity.getDataEmprestimo(),
                entity.getDataDevolucaoPrevista(),
                entity.getDiasAtrasados(),
                entity.getQtdRenovado(),
                entity.getStatusEmprestimo()
        );
    }
}
