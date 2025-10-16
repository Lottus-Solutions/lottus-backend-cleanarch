package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ListarLivrosUseCase;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Livro;

import java.util.List;

public class ListarLivrosUseCaseImpl implements ListarLivrosUseCase {

    private final LivroRepositoryPort livroRepositoryPort;

    public ListarLivrosUseCaseImpl(LivroRepositoryPort livroRepositoryPort) {
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Override
    public List<Livro> executar() {
        return livroRepositoryPort.findAll();
    }
}
