package br.com.lottus.library.infrastructure.persistence.jpa.repository.spring;

import br.com.lottus.library.domain.entities.StatusEmprestimo;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.AlunoEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {
    List<EmprestimoEntity> findByAlunoAndStatusEmprestimoIn(AlunoEntity aluno, List<StatusEmprestimo> statuses);
    Optional<EmprestimoEntity> findFirstByAlunoAndStatusEmprestimoInOrderByDataEmprestimoDesc(AlunoEntity aluno, List<StatusEmprestimo> statuses);

    @Query("SELECT e FROM EmprestimoEntity e WHERE e.statusEmprestimo IN :statuses " +
           "AND (:busca IS NULL OR e.aluno.nome LIKE %:busca% OR e.livro.nome LIKE %:busca%) " +
           "AND (:apenasAtrasados = false OR e.dataDevolucaoPrevista < CURRENT_DATE)")
    Page<EmprestimoEntity> findAllFiltered(@Param("statuses") List<StatusEmprestimo> statuses, 
                                          @Param("apenasAtrasados") boolean apenasAtrasados, 
                                          @Param("busca") String busca, 
                                          Pageable pageable);

    List<EmprestimoEntity> findByStatusEmprestimo(StatusEmprestimo status);

    List<EmprestimoEntity> findTop7ByAlunoAndStatusEmprestimoOrderByDataEmprestimoDesc(AlunoEntity aluno, StatusEmprestimo status);
}
