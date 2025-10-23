package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.AlunoEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlunoRepositoryAdapter implements AlunoRepositoryPort {

    private final AlunoRepository repository;

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
    public List<Aluno> findAllByTurma(Turma turma) {
        var turmaEntity = br.com.lottus.library.infrastructure.persistence.jpa.mapper.TurmaEntityMapper.toEntity(turma);
        return repository.findAllByTurma(turmaEntity).stream()
                .map(AlunoEntityMapper::toDomain)
                .collect(Collectors.toList());
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