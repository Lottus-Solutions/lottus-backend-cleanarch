package br.com.lottus.library.infrastructure.persistence.repository;

import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.infrastructure.persistence.entity.UsuarioEntity;
import br.com.lottus.library.infrastructure.persistence.mapper.UsuarioMapper;
import br.com.lottus.library.infrastructure.persistence.repository.spring.JpaUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdaptar implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRepositoryAdaptar(JpaUsuarioRepository jpaUsuarioRepository, UsuarioMapper usuarioMapper) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        UsuarioEntity savedEntity = jpaUsuarioRepository.save(entity);
        return usuarioMapper.toDomain(savedEntity);
    }

    

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaUsuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaUsuarioRepository.findById(id)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUsuarioRepository.existsByEmail(email);
    }
}
