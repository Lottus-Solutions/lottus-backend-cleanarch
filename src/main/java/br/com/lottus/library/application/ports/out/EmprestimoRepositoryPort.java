package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lottus.library.domain.entities.Livro;

public interface EmprestimoRepositoryPort {
    Emprestimo save(Emprestimo emprestimo);
    List<Emprestimo> findAll();
    Optional<Emprestimo> findById(Long id);
    void deleteById(Long id);
    List<Emprestimo> findByAlunoAndStatusEmprestimoIn(Aluno aluno, List<StatusEmprestimo> statuses);
    Optional<Emprestimo> findFirstByAlunoAndStatusEmprestimoInOrderByDataEmprestimoDesc(Aluno aluno, List<StatusEmprestimo> statuses);
    Page<Emprestimo> findAllFiltered(List<StatusEmprestimo> statuses, boolean apenasAtrasados, String busca, Pageable pageable);
    List<Emprestimo> findTop7ByLivroAndStatusEmprestimoOrderByDataEmprestimoDesc(Livro livro, StatusEmprestimo status);
    List<Emprestimo> findTop7ByAlunoAndStatusEmprestimoOrderByDataEmprestimoDesc(Aluno aluno, StatusEmprestimo status);
    List<Emprestimo> findByStatusEmprestimo(StatusEmprestimo status);
}
