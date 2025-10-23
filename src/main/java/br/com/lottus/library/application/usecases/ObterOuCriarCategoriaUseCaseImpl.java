package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.application.ports.in.ObterOuCriarCategoriaUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;

public class ObterOuCriarCategoriaUseCaseImpl implements ObterOuCriarCategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepository;

    public ObterOuCriarCategoriaUseCaseImpl(CategoriaRepositoryPort categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria executar(CadastrarCategoriaCommand command) {
        return categoriaRepository.findByNome(command.nome())
                .orElseGet(() -> {
                    Categoria novaCategoria = Categoria.criar(command.nome(), "#0292B7");
                    return categoriaRepository.save(novaCategoria);
                });
    }
}
