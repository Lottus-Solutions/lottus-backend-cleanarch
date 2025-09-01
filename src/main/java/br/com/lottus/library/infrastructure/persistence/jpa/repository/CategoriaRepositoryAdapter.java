package br.com.lottus.library.infrastructure.persistence.jpa.repository;

import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.infrastructure.persistence.jpa.entity.CategoriaEntity;
import br.com.lottus.library.infrastructure.persistence.jpa.mapper.CategoriaEntityMapper;
import br.com.lottus.library.infrastructure.persistence.jpa.repository.spring.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaRepository repository;

    public CategoriaRepositoryAdapter(CategoriaRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean existsByNome(String nome) {
        return repository.existsByNome(nome);
    }

    @Override
    public Categoria save(Categoria domain) {
        CategoriaEntity categoriaParaRegistro = CategoriaEntityMapper.toEntity(domain);
        CategoriaEntity categoriaRegistrada = repository.save(categoriaParaRegistro);
        return CategoriaEntityMapper.toDomain(categoriaRegistrada);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Categoria> findAll() {
        List<CategoriaEntity> categoriaEntities = repository.findAll();
        List<Categoria> categoriaDomains = new ArrayList<>();

        for (CategoriaEntity categoria : categoriaEntities) {
            var categoriaDomain = CategoriaEntityMapper.toDomain(categoria);
            categoriaDomains.add(categoriaDomain);
        }

        return categoriaDomains;
    }

    @Override
    public void deleteById(Long id) {

    }
}
