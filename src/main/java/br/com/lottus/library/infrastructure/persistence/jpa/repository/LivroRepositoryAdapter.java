package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.LivroEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.CategoriaRepository;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroRepositoryAdapter implements LivroRepositoryPort {

    private final LivroRepository repository;
    private final CategoriaRepository categoriaRepository;

    public LivroRepositoryAdapter(LivroRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Livro save(Livro domain) {
        var categoriaEntity =categoriaRepository.getReferenceById(domain.getCategoria().getId());
        var livroParaRegistro = LivroEntityMapper.toEntity(domain, categoriaEntity);

        var livroRegistrado = repository.save(livroParaRegistro);
        return LivroEntityMapper.toDomain(livroRegistrado);
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
