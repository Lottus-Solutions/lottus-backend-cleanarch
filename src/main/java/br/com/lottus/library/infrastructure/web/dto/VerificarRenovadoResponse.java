package br.com.lottus.library.infrastructure.web.dto;

import java.io.Serializable;

public record VerificarRenovadoResponse(
        boolean foiRenovado,
        int quantidadeRenovacoes
) implements Serializable {
}
