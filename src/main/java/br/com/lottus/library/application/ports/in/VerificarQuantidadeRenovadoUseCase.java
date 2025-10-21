package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.infrastructure.web.dto.VerificarRenovadoResponse;

public interface VerificarQuantidadeRenovadoUseCase {
    VerificarRenovadoResponse executar(Long emprestimoId);
}
