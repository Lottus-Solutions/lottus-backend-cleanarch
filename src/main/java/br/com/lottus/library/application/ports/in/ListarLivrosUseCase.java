package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Livro;

import java.util.List;

public interface ListarLivrosUseCase {
    List<Livro> executar();
}
