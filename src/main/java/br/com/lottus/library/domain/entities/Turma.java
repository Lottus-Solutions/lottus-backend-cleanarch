package br.com.lottus.library.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class Turma implements Serializable {
    private Long id;
    private String serie;

    private Turma(Long id, String serie) {
        this.id = id;
        this.serie = Objects.requireNonNull(serie, "Série da turma não pode ser nula");
    }

    // Construtor para frameworks (Jackson, Hibernate)
    protected Turma() {}

    public static Turma criar(String serie) {
        return new Turma(null, serie);
    }

    public static Turma criarComId(Long id, String serie) {
        return new Turma(id, serie);
    }

    public void alterarNome(String serie) {
        this.serie = Objects.requireNonNull(serie, "Série da turma não pode ser nula");
    }

    public Long getId() {
        return id;
    }

    public String getSerie() {
        return serie;
    }
}
