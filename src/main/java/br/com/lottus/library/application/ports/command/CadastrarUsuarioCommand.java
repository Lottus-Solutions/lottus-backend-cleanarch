package br.com.lottus.library.application.ports.command;

import java.time.LocalDate;

public record CadastrarUsuarioCommand(String nome, String email, String senha, Integer idAvatar,
                                      LocalDate dataRegistro) {
}
