package br.com.lottus.library.domain.entities;

import java.util.Objects;

public class Turma {
    private Long id;
    private String nome;

    private Turma(Long id, String nome) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "Nome da turma não pode ser nulo");
    }

    public static Turma criar(String nome) {
        return new Turma(null, nome);
    }

    public static Turma criarComId(Long id, String nome) {
        return new Turma(id, nome);
    }

    public void alterarNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome da turma não pode ser nulo");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
