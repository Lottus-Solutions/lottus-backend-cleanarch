package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.exceptions.TurmaNaoEncontradaException;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.AlunoEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.TurmaEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.AlunoEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.TurmaEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.AlunoRepository;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlunoRepositoryAdapter implements AlunoRepositoryPort {

    private final AlunoRepository repository;
    private final TurmaRepository turmaRepository;

    @Override
    public Aluno save(Aluno aluno) {
        var entity = AlunoEntityMapper.toEntity(aluno);
        return AlunoEntityMapper.toDomain(repository.save(entity));
    }

    @Override
    public List<Aluno> findAll() {
        return repository.findAll().stream()
                .map(AlunoEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return repository.findById(id).map(AlunoEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Aluno> findAllByTurma(Turma turma, Pageable pageable) {
        TurmaEntity turmaEntity = turmaRepository.findById(turma.getId())
                .orElseThrow(TurmaNaoEncontradaException::new);

        Page<AlunoEntity> pageEntity = repository.findAllByTurma(turmaEntity, pageable);

        return pageEntity.map(AlunoEntityMapper::toDomain);
    }

    @Override
        public List<Aluno> findByNomeContainingAndTurmaId(String nome, Long turmaId) {
            return repository.findByNomeContainingAndTurmaId(nome, turmaId).stream()
                    .map(AlunoEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }

        @Override
        public List<Aluno> findAllByNomeContainingIgnoreCase(String nome) {
            return repository.findAllByNomeContainingIgnoreCase(nome).stream()
                    .map(AlunoEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }
    }