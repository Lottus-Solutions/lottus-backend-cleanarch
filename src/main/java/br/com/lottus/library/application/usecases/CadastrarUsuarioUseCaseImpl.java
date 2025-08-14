package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.stream.Stream;

public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {

    public CadastrarUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return Stream.of(usuario)
                .map(u -> {
                    Usuario novo = new Usuario();
                    novo.setNome(u.getNome());
                    novo.setEmail(u.getEmail());
                    novo.setSenha(u.getSenha());
                    novo.setIdAvatar(u.getIdAvatar());
                    novo.setDataRegistro(LocalDate.now());
                    return novo;
                })
                .findFirst()
                .orElse(null);
    }
}
