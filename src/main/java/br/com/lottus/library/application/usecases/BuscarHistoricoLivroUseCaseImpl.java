package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import br.com.lottus.library.application.ports.in.BuscarHistoricoLivroUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusEmprestimo;

import java.util.List;

public class BuscarHistoricoLivroUseCaseImpl implements BuscarHistoricoLivroUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private final LivroRepositoryPort livroRepositoryPort;

    public BuscarHistoricoLivroUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort, LivroRepositoryPort livroRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Override
    public List<Emprestimo> executar(Long livroId) {
        Livro livro = livroRepositoryPort.findById(livroId)
                .orElseThrow(LivroNaoEncontradoException::new);

        return emprestimoRepositoryPort.findTop7ByLivroAndStatusEmprestimoOrderByDataEmprestimoDesc(livro, StatusEmprestimo.FINALIZADO);
    }
}
