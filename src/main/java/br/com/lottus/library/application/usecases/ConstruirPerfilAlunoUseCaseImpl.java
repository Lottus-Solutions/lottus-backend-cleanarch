package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.AlunoNaoEncontradoException;
import br.com.lottus.library.application.ports.in.ConstruirPerfilAlunoUseCase;
import br.com.lottus.library.application.ports.out.AlunoRepositoryPort;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Aluno;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.Livro;
import br.com.lottus.library.domain.entities.StatusEmprestimo;
import br.com.lottus.library.infrastructure.web.dto.AlunoDTO;
import br.com.lottus.library.infrastructure.web.dto.PerfilAlunoResponse;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConstruirPerfilAlunoUseCaseImpl implements ConstruirPerfilAlunoUseCase {

    private final AlunoRepositoryPort alunoRepositoryPort;
    private final EmprestimoRepositoryPort emprestimoRepositoryPort;

    public ConstruirPerfilAlunoUseCaseImpl(AlunoRepositoryPort alunoRepositoryPort, EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.alunoRepositoryPort = alunoRepositoryPort;
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    public PerfilAlunoResponse executar(Long matricula) {
        Aluno aluno = alunoRepositoryPort.findById(matricula)
                .orElseThrow(AlunoNaoEncontradoException::new);

        List<StatusEmprestimo> statusAtrasado = Collections.singletonList(StatusEmprestimo.ATRASADO);
        List<Emprestimo> emprestimosAtrasados = emprestimoRepositoryPort.findByAlunoAndStatusEmprestimoIn(aluno, statusAtrasado);
        boolean temAtraso = !emprestimosAtrasados.isEmpty();

        List<StatusEmprestimo> statusParaLivroAtual = List.of(StatusEmprestimo.ATIVO, StatusEmprestimo.ATRASADO);
        Optional<Emprestimo> optionalEmprestimoAtual = emprestimoRepositoryPort.findFirstByAlunoAndStatusEmprestimoInOrderByDataEmprestimoDesc(aluno, statusParaLivroAtual);

        Livro livroEmprestado = optionalEmprestimoAtual.map(Emprestimo::getLivro).orElse(null);
        LocalDate dataDevolucaoPrevista = optionalEmprestimoAtual.map(Emprestimo::getDataDevolucaoPrevista).orElse(null);
        String nomeLivroAtual = optionalEmprestimoAtual.map(e -> e.getLivro().getNome()).orElse(null);

        AlunoDTO alunoDTO = new AlunoDTO(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getQtdBonus(),
                aluno.getTurma().getId(),
                aluno.getQtdLivrosLidos(),
                nomeLivroAtual
        );

        return new PerfilAlunoResponse(alunoDTO, temAtraso, livroEmprestado, dataDevolucaoPrevista);
    }
}
