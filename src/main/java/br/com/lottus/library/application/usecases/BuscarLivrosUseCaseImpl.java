package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.in.BuscarLivrosUseCase;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;
import br.com.lottus.library.domain.entities.StatusLivro;
import br.com.lottus.library.infrastructure.web.dto.LivroResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
public class BuscarLivrosUseCaseImpl implements BuscarLivrosUseCase {

    private final LivroRepositoryPort livroRepositoryPort;

    public BuscarLivrosUseCaseImpl(LivroRepositoryPort livroRepositoryPort) {
        this.livroRepositoryPort = livroRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LivroResponseDTO> executar(String termoBusca, String status, Long categoriaId, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("id").descending());

        String termoNormalizado = (termoBusca == null || termoBusca.isBlank()) ? null : termoBusca.trim();
        List<StatusLivro> statusList = mapStatusParaLista(status);

        return livroRepositoryPort.buscarLivros(termoBusca,statusList, categoriaId, pageable);
    }

    private List<StatusLivro> mapStatusParaLista(String status) {
        if (status == null || status.isBlank()) {
            return List.of(StatusLivro.values());
        }
        return List.of(StatusLivro.fromString(status.trim()));
    }
}
