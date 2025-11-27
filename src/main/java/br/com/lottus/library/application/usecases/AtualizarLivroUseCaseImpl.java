package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.CategoriaNaoEncontradaException;
import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import br.com.lottus.library.application.ports.in.AtualizarLivroUseCase;
import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.infrastructure.web.command.AtualizarLivroCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;

@Slf4j
public class AtualizarLivroUseCaseImpl implements AtualizarLivroUseCase {

    private final LivroRepositoryPort livroPort;
    private final CategoriaRepositoryPort categoriaPort;

    public AtualizarLivroUseCaseImpl(LivroRepositoryPort livroPort, CategoriaRepositoryPort categoriaPort) {
        this.livroPort = livroPort;
        this.categoriaPort = categoriaPort;
    }

    @Override
    @CacheEvict(value = {"livros", "categorias"}, allEntries = true)
    public Livro executar(Long id, AtualizarLivroCommand command) {
        log.info("Iniciando atualização do livro com ID: {}", id);

        Livro livroExistente = livroPort.findById(id)
                .orElseThrow(() -> {
                    log.warn("Livro com ID {} não encontrado", id);
                    return new LivroNaoEncontradoException();
                });

        Categoria categoria = categoriaPort.findById(command.categoriaId())
                        .orElseThrow(() -> {
                            log.warn("Categoria com ID {} não encontrada ao atualizar livro ", command.categoriaId());
                            return new CategoriaNaoEncontradaException();
                        });

        log.debug("Atualizando informações do livro '{}'", livroExistente.getNome());

        livroExistente.alterarNome(command.nome());
        livroExistente.alterarAutor(command.autor());
        livroExistente.alterarQuantidade(command.quantidade());
        livroExistente.alterarQuantidadeDisponivel(command.quantidadeDisponivel());
        livroExistente.alterarStatus(command.status());
        livroExistente.alterarDescricao(command.descricao());
        livroExistente.alterarCategoria(categoria);

        Livro atualizado = livroPort.save(livroExistente);

        log.info("Livro atualizado com sucesso: ID = {}, Nome = {}", atualizado.getId(), atualizado.getNome());

        return atualizado;
    }
}
