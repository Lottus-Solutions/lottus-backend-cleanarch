package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ListarCategoriaUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;

import java.util.List;

public class ListarCategoriaImpl implements ListarCategoriaUseCase {

    private final CategoriaRepositoryPort port;

    public ListarCategoriaImpl(CategoriaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public List<Categoria> executar() {
        return port.findAll();
    }
}
