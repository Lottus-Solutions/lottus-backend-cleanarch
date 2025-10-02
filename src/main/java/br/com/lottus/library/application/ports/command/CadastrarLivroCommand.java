package br.com.lottus.library.application.ports.command;

public record CadastrarLivroCommand(
    String nome,
    String autor,
    Integer quantidade,
    Long categoriaId,
    String descricao
) {
}
