package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.exceptions.LivroJaCadastradoException;
import br.com.lottus.library.application.ports.command.CadastrarLivroCommand;
import br.com.lottus.library.application.ports.in.CadastrarLivroUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusLivro;
import org.springframework.cache.annotation.CacheEvict;

public class CadastrarLivroImpl implements CadastrarLivroUseCase {

    private final LivroRepositoryPort livroPort;
    private final CategoriaRepositoryPort categoriaPort;

    public CadastrarLivroImpl(LivroRepositoryPort livroPort, CategoriaRepositoryPort categoriaPort) {
        this.livroPort = livroPort;
        this.categoriaPort = categoriaPort;
    }

    @Override
    @CacheEvict(value = {"livros", "categorias"}, allEntries = true)
    public Livro executar(CadastrarLivroCommand command) {
        Boolean existe = livroPort.existsByNomeIgnoreCase(command.nome());
        if (Boolean.TRUE.equals(existe)) {
            throw new LivroJaCadastradoException();
        }

        Categoria categoria = categoriaPort.findById(command.categoriaId()).
                orElseThrow(CategoriaNaoEncontradaException::new);

        Livro livro = Livro.criar(
                command.nome(),
                command.autor(),
                categoria,
                command.quantidade(),
                StatusLivro.DISPONIVEL,
                command.descricao()
        );

        return livroPort.save(livro);
    }
}
