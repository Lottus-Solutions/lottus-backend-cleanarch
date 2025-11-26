package br.com.lottus.library.domain.entities;

import br.com.lottus.library.domain.exceptions.StatusInvalidoException;

import java.util.Arrays;

public enum StatusLivro {
    DISPONIVEL,
    RESERVADO;

    public static StatusLivro fromString(String status) {
        return Arrays.stream(StatusLivro.values())
                .filter(s -> s.name().equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new StatusInvalidoException(status));
    }
}
