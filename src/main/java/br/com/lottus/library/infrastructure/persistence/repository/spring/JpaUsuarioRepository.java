package br.com.lottus.library.infrastructure.persistence.repository.spring;

import aj.org.objectweb.asm.commons.Remapper;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
