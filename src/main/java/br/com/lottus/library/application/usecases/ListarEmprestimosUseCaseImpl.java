package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.ListarEmprestimosUseCase;
import br.com.lottus.library.application.ports.out.EmprestimoRepositoryPort;
import br.com.lottus.library.domain.entities.Emprestimo;
import br.com.lottus.library.domain.entities.StatusEmprestimo;
import br.com.lottus.library.infrastructure.web.dto.EmprestimoResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

public class ListarEmprestimosUseCaseImpl implements ListarEmprestimosUseCase {

    private final EmprestimoRepositoryPort emprestimoRepositoryPort;

    public ListarEmprestimosUseCaseImpl(EmprestimoRepositoryPort emprestimoRepositoryPort) {
        this.emprestimoRepositoryPort = emprestimoRepositoryPort;
    }

    @Override
    @Cacheable(value = "emprestimos", key = "{#busca, #atrasados, #pageable}")
    public Page<EmprestimoResponseDTO> executar(String busca, boolean atrasados, Pageable pageable) {
        List<StatusEmprestimo> statusList = Arrays.asList(StatusEmprestimo.ATIVO, StatusEmprestimo.ATRASADO);

        Page<Emprestimo> emprestimos = emprestimoRepositoryPort.findAllFiltered(statusList, atrasados, busca, pageable);

        return emprestimos.map(emprestimo -> new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getAluno().getNome(),
                emprestimo.getAluno().getTurma().getSerie(),
                emprestimo.getLivro().getNome(),
                emprestimo.getDataDevolucaoPrevista(),
                emprestimo.getDiasAtrasadosCalculado()
        ));
    }
}
