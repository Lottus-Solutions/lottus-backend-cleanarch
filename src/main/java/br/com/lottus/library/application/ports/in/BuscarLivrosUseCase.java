package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.infrastructure.web.dto.LivroResponseDTO;
import org.springframework.data.domain.Page;

public interface BuscarLivrosUseCase {
    Page<LivroResponseDTO> executar(String termoBusca, String status, Long categoriaId, int pagina, int tamanho);
}
