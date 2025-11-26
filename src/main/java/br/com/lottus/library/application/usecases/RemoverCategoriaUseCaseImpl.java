package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.ports.in.RemoverCategoriaUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;

public class RemoverCategoriaUseCaseImpl implements RemoverCategoriaUseCase {

    private final CategoriaRepositoryPort port;

    public RemoverCategoriaUseCaseImpl(CategoriaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public void executar(Long id) {
        if (!categoriaExiste(id)) {
            throw new CategoriaNaoEncontradaException();
        }

        port.deleteById(id);
    }

    private boolean categoriaExiste(Long id) {
        return port.existsById(id);
    }
}
