package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.LivroEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.LivroEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.LivroRepository;

import java.util.List;
import java.util.Optional;

public class LivroRepositoryAdapter implements LivroRepositoryPort {

    private final LivroRepository repository;

    public LivroRepositoryAdapter(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public Livro save(Livro domain) {
        LivroEntity livroParaRegistro = LivroEntityMapper.toEntity(domain);
        LivroEntity livroRegistrado = repository.save(livroParaRegistro);
        return LivroEntityMapper.toDomain(livroRegistrado);
    }

    @Override
    public List<Livro> findAll() {
        return List.of();
    }

    @Override
    public Optional<Livro> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Integer countByCategoriaId(Long categoriaId) {
        return 0;
    }

    @Override
    public Boolean existsByNomeIgnoreCase(String nome) {
        return null;
    }
}
