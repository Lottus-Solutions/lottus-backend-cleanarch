package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.TurmaRepositoryPort;
import br.com.lottus.library.domain.entities.Turma;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.TurmaEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TurmaRepositoryAdapter implements TurmaRepositoryPort {

    private final TurmaRepository repository;

    @Override
    public Turma save(Turma turma) {
        var entity = TurmaEntityMapper.toEntity(turma);
        return TurmaEntityMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Turma> findById(Long id) {
        return repository.findById(id).stream().map(TurmaEntityMapper::toDomain).findFirst();
    }

    @Override
    public List<Turma> findAll() {
        return repository.findAll().stream().map(TurmaEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return repository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public Optional<Turma> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .findFirst() // Pega o primeiro se houver duplicatas
                .map(TurmaEntityMapper::toDomain);
    }
}
