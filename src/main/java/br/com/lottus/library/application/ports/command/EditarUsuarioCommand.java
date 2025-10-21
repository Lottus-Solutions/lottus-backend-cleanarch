package br.com.lottus.library.application.ports.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditarUsuarioCommand(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotNull
        Integer idAvatar
) {
}
