package br.com.lottus.library.infrastructure.persistence.mapper;

import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsuarioMapper {

    /**
     * Converte uma entidade JPA Usuaario para um objeto de dominio Usuario.
     * @param entity a entidade vinda do banco de dados
     * @return o objeto de dominio Usuario
     */
    public Usuario toDomain(UsuarioEntity entity) {
        if (Objects.isNull(entity)) return null;

        return Usuario.criarComId(entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha(),
                entity.getDataCadastro(),
                entity.getIdAvatar());
    }

    /**
     * Converte um objeto de domnio Usuario para uma entidade JPA UsuarioEntity.
     * @param domain o objeto de dominio Usuario
     * @return a entidade JPA UsuarioEntity
     */
    public UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) return null;

       UsuarioEntity entity = new UsuarioEntity();

       entity.setNome(domain.getNome());
       entity.setEmail(domain.getEmail());
       entity.setSenha(domain.getSenha());
       entity.setIdAvatar(domain.getIdAvatar());
       entity.setDataCadastro(domain.getDataRegistro());
       return entity;
    }


}
