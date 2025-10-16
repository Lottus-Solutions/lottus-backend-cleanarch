package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import br.com.lottus.library.application.ports.in.AtualizarLivroUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.web.command.AtualizarLivroCommand;

public class AtualizarLivroUseCaseImpl implements AtualizarLivroUseCase {

    private final LivroRepositoryPort livroPort;
    private final CategoriaRepositoryPort categoriaPort;

    public AtualizarLivroUseCaseImpl(LivroRepositoryPort livroPort, CategoriaRepositoryPort categoriaPort) {
        this.livroPort = livroPort;
        this.categoriaPort = categoriaPort;
    }

    @Override
    public Livro executar(Long id, AtualizarLivroCommand command) {
        Livro livroExistente = livroPort.findById(id)
                .orElseThrow(LivroNaoEncontradoException::new);

        Categoria categoria = categoriaPort.findById(command.categoriaId())
                        .orElseThrow(CategoriaNaoEncontradaException::new);

        livroExistente.alterarNome(command.nome());
        livroExistente.alterarAutor(command.autor());
        livroExistente.alterarQuantidade(command.quantidade());
        livroExistente.alterarQuantidadeDisponivel(command.quantidadeDisponivel());
        livroExistente.alterarStatus(command.status());
        livroExistente.alterarDescricao(command.descricao());
        livroExistente.alterarCategoria(categoria);

        return livroPort.save(livroExistente);
    }
}
