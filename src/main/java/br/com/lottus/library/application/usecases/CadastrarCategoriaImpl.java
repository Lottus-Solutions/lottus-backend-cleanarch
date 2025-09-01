package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.application.exceptions.CategoriaJaExistenteException;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;

public class CadastrarCategoriaImpl {

    private final CategoriaRepositoryPort port;

    public CadastrarCategoriaImpl(CategoriaRepositoryPort port) {
        this.port = port;
    }

    public Categoria executar(CadastrarCategoriaCommand comando) {
        if (port.existsByNome(comando.nome())) {
            throw new CategoriaJaExistenteException();
        }

        var domain = Categoria.criar(comando.nome(), comando.cor());
        return port.save(domain);
    }
}
