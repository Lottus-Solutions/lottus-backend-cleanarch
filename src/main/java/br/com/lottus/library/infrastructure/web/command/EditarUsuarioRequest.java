package br.com.lottus.library.infrastructure.web.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditarUsuarioRequest(
        @NotBlank(message = "O nome não pode ser vazio.")
        String nome,
        @NotNull(message = "O id do avatar não pode ser nulo.")
        Integer idAvatar
) {
}
