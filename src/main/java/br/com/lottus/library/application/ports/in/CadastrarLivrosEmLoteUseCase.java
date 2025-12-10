package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.infrastructure.web.dto.LivroBatchDTO;
import java.util.List;

public interface CadastrarLivrosEmLoteUseCase {
    void cadastrarLivros(List<LivroBatchDTO> livros);
}
