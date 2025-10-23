package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.BuscarLivrosUseCase;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;

import java.util.List;

public class BuscarLivrosUseCaseImpl implements BuscarLivrosUseCase {

    private final LivroRepositoryPort livroRepositoryPort;

    public BuscarLivrosUseCaseImpl(LivroRepositoryPort livroRepositoryPort) {
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Override
    public List<Livro> executar() {
        return livroRepositoryPort.findAll();
    }
}
