package br.com.lottus.library.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno implements Serializable {

    private Long matricula;
    private String nome;
    private Double qtdBonus;
    private Integer qtdLivrosLidos;
    private Turma turma;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    private Aluno(Long matricula, String nome, Turma turma) {
        this.matricula = matricula;
        this.nome = validarNome(nome);
        this.turma = Objects.requireNonNull(turma, "Turma não pode ser nula");
        this.qtdBonus = 0.0;
        this.qtdLivrosLidos = 0;
    }

    // Construtor para frameworks (Jackson, Hibernate)
    protected Aluno() {}

    public static Aluno criar(String nome, Turma turma) {
        return new Aluno(null, nome, turma);
    }

    public static Aluno criarComId(Long matricula, String nome, Turma turma, Double qtdBonus, Integer qtdLivrosLidos, List<Emprestimo> emprestimos) {
        Aluno aluno = new Aluno(matricula, nome, turma);
        aluno.qtdBonus = Objects.requireNonNull(qtdBonus, "Quantidade de bônus não pode ser nula");
        aluno.qtdLivrosLidos = Objects.requireNonNull(qtdLivrosLidos, "Quantidade de livros lidos não pode ser nula");
        aluno.emprestimos = Objects.requireNonNull(emprestimos, "Lista de empréstimos não pode ser nula");
        return aluno;
    }

    private String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio ou nulo");
        }
        return nome;
    }

    public void resetarBonus() {
        this.qtdBonus = 0.0;
    }

    public void resetarLivrosLidos() {
        this.qtdLivrosLidos = 0;
    }

    public Boolean podeFazerEmprestimo() {
        return emprestimos.stream()
                .noneMatch(emprestimo -> emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATIVO);
    }

    public void incrementarLivrosLidos() {
        this.qtdLivrosLidos++;
        if (this.qtdLivrosLidos > 4) {
            this.qtdBonus += 0.25;
        }
    }

    public void alterarNome(String nome) {
        this.nome = validarNome(nome);
    }

    public void alterarQtdBonus(Double qtdBonus) {
        this.qtdBonus = Objects.requireNonNull(qtdBonus, "Quantidade de bônus não pode ser nula");
    }

    public void alterarQtdLivrosLidos(Integer qtdLivrosLidos) {
        this.qtdLivrosLidos = Objects.requireNonNull(qtdLivrosLidos, "Quantidade de livros lidos não pode ser nula");
    }

    public void alterarTurma(Turma turma) {
        this.turma = Objects.requireNonNull(turma, "Turma não pode ser nula");
    }

    // Getters
    public Long getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public Double getQtdBonus() {
        return qtdBonus;
    }

    public Integer getQtdLivrosLidos() {
        return qtdLivrosLidos;
    }

    public Turma getTurma() {
        return turma;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
