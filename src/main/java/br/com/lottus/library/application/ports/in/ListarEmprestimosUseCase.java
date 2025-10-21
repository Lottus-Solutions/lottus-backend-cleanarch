package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.infrastructure.web.dto.EmprestimoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarEmprestimosUseCase {
    Page<EmprestimoResponseDTO> executar(String busca, boolean atrasados, Pageable pageable);
}
