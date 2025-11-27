package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.EmprestimoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.FinalizarEmprestimoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.Livro;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;

public class FinalizarEmprestimoUseCaseImpl implements FinalizarEmprestimoUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;
    private final AlunoRepositoryPort alunoRepositoryPort;
    private final LivroRepositoryPort livroRepositoryPort;

    public FinalizarEmprestimoUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort, AlunoRepositoryPort alunoRepositoryPort, LivroRepositoryPort livroRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Transactional
    @Override
    @CacheEvict(value = {"emprestimos", "livros", "alunos"}, allEntries = true)
    public void executar(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepositoryPort.findById(emprestimoId)
                .orElseThrow(EmprestimoNaoEncontradoException::new);

        emprestimo.finalizar();

        Aluno aluno = emprestimo.getAluno();
        aluno.incrementarLivrosLidos();

        Livro livro = emprestimo.getLivro();
        livro.devolver();

        emprestimoRepositoryPort.save(emprestimo);
        alunoRepositoryPort.save(aluno);
        livroRepositoryPort.save(livro);
    }
}
