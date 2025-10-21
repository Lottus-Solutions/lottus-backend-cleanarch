package br.com.lottus.library.infrastructure.web.dto;

public record VerificarRenovadoResponse(
        boolean foiRenovado,
        int quantidadeRenovacoes
) {
}
