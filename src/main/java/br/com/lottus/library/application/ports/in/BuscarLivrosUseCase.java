package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BuscarLivrosUseCase {
    List<Livro> executar();
}
