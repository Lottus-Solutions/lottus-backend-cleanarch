package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.AlunoEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.EmprestimoEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.EmprestimoEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.AlunoRepository;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.EmprestimoRepository;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmprestimoRepositoryAdapter implements EmprestimoRepositoryPort {

    private final EmprestimoRepository repository;
    private final LivroRepository livroRepository;
    private final AlunoRepository alunoRepository;

    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        EmprestimoEntity entity;
        if (emprestimo.getId() != null) {
            // Atualização: Carrega a entidade existente
            entity = repository.findById(emprestimo.getId())
                    .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado para atualização"));

            // Atualiza os campos da entidade gerenciada
            entity.setStatusEmprestimo(emprestimo.getStatusEmprestimo());
            entity.setDataDevolucaoPrevista(emprestimo.getDataDevolucaoPrevista());
            entity.setDiasAtrasados(emprestimo.getDiasAtrasados());
            entity.setQtdRenovado(emprestimo.getQtdRenovado());

        } else {
            // Criação: Cria uma nova entidade
            var alunoEntity = alunoRepository.findById(emprestimo.getAluno().getMatricula()).orElseThrow();
            var livroEntity = livroRepository.findById(emprestimo.getLivro().getId()).orElseThrow();
            entity = EmprestimoEntityMapper.toEntity(emprestimo, alunoEntity, livroEntity);
        }

        var savedEntity = repository.save(entity);
        return EmprestimoEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Emprestimo> findAll() {
        return repository.findAll().stream()
                .map(entity -> EmprestimoEntityMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Emprestimo> findById(Long id) {
        return repository.findById(id).map(entity -> EmprestimoEntityMapper.toDomain(entity));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Emprestimo> findByAlunoAndStatusEmprestimoIn(br.com.lottus.library.domain.entities.Aluno aluno, List<br.com.lottus.library.domain.entities.StatusEmprestimo> statuses) {
        var alunoEntity = br.com.lottus.library.infrastructure.persistence.jpa.mapper.AlunoEntityMapper.toEntity(aluno);
        return repository.findByAlunoAndStatusEmprestimoIn(alunoEntity, statuses).stream()
                .map(entity -> EmprestimoEntityMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Emprestimo> findByStatusEmprestimo(br.com.lottus.library.domain.entities.StatusEmprestimo status) {
        return repository.findByStatusEmprestimo(status).stream()
                .map(entity -> EmprestimoEntityMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Emprestimo> findTop7ByAlunoAndStatusEmprestimoOrderByDataEmprestimoDesc(br.com.lottus.library.domain.entities.Aluno aluno, br.com.lottus.library.domain.entities.StatusEmprestimo status) {
        var alunoEntity = br.com.lottus.library.infrastructure.persistence.jpa.mapper.AlunoEntityMapper.toEntity(aluno);
        return repository.findTop7ByAlunoAndStatusEmprestimoOrderByDataEmprestimoDesc(alunoEntity, status).stream()
                .map(entity -> EmprestimoEntityMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Emprestimo> findTop7ByLivroAndStatusEmprestimoOrderByDataEmprestimoDesc(br.com.lottus.library.domain.entities.Livro livro, br.com.lottus.library.domain.entities.StatusEmprestimo status) {
        var livroEntity = br.com.lottus.library.infrastructure.persistence.jpa.mapper.LivroEntityMapper.toEntity(livro, br.com.lottus.library.infrastructure.persistence.jpa.mapper.CategoriaEntityMapper.toEntity(livro.getCategoria()));
        return repository.findTop7ByLivroAndStatusEmprestimoOrderByDataEmprestimoDesc(livroEntity, status).stream()
                .map(entity -> EmprestimoEntityMapper.toDomain(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Emprestimo> findAllFiltered(List<StatusEmprestimo> statuses, boolean apenasAtrasados, String busca, Pageable pageable) {
        return repository.findAllFiltered(statuses, apenasAtrasados, busca, pageable)
                .map(entity -> EmprestimoEntityMapper.toDomain(entity));
    }

    @Override
    public Optional<Emprestimo> findFirstByAlunoAndStatusEmprestimoInOrderByDataEmprestimoDesc(br.com.lottus.library.domain.entities.Aluno aluno, List<br.com.lottus.library.domain.entities.StatusEmprestimo> statuses) {
        AlunoEntity alunoEntity = br.com.lottus.library.infrastructure.persistence.jpa.mapper.AlunoEntityMapper.toEntity(aluno);
        return repository.findFirstByAlunoAndStatusEmprestimoInOrderByDataEmprestimoDesc(alunoEntity, statuses)
                .map(EmprestimoEntityMapper::toDomain);
    }
}
