package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.BuscarLivrosUseCase;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Slf4j
public class BuscarLivrosUseCaseImpl implements BuscarLivrosUseCase {

    private final LivroRepositoryPort livroRepositoryPort;

    public BuscarLivrosUseCaseImpl(LivroRepositoryPort livroRepositoryPort) {
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Override
    public List<Livro> executar() {
        log.info("Buscando todos os livros");
        return livroRepositoryPort.findAll();
    }
}
