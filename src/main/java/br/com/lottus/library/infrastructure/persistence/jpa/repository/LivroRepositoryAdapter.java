package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.CategoriaEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.LivroEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.CategoriaRepository;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.LivroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LivroRepositoryAdapter implements LivroRepositoryPort {

    private final LivroRepository repository;
    private final CategoriaRepository categoriaRepository;

    public LivroRepositoryAdapter(LivroRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Livro save(Livro domain) {
        log.debug("Salvando livro no banco de dados (Nome: {})", domain.getNome());
        LivroEntity entity;

        if (domain.getId() != null) {
            entity = repository.findById(domain.getId())
                    .orElse(new LivroEntity());
            LivroEntityMapper.atualizarEntity(entity, domain, entity.getCategoria());
        } else {
            CategoriaEntity categoriaEntity = categoriaRepository.findById(domain.getCategoria().getId())
                    .orElseThrow(CategoriaNaoEncontradaException::new);
            entity = LivroEntityMapper.toEntity(domain, categoriaEntity);
        }

        return LivroEntityMapper.toDomain(repository.save(entity));
    }

    @Override
    public List<Livro> findAll() {
        return repository.findAll()
                .stream()
                .map(LivroEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Livro> findById(Long id) {
        log.debug("Consultando livro no banco de dados (ID: {})", id);
        return repository.findById(id)
                .map(LivroEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Integer countByCategoriaId(Long categoriaId) {
        return repository.countByCategoriaId(categoriaId);
    }

    @Override
    public Boolean existsByNomeIgnoreCase(String nome) {
        return repository.existsByNomeIgnoreCase(nome);
    }
}
