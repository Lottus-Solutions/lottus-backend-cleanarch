package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);
    Optional<Usuario> findByIdAndSenha(String email, String senha);
}
