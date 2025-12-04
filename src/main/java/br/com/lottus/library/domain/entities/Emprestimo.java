package br.com.lottus.library.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo implements Serializable {

    private Long id;
    private Aluno aluno;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private int diasAtrasados;
    private int qtdRenovado;
    private StatusEmprestimo statusEmprestimo;

    private Emprestimo(Long id, Aluno aluno, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, StatusEmprestimo status) {
        this.id = id;
        this.aluno = Objects.requireNonNull(aluno, "Aluno não pode ser nulo");
        this.livro = Objects.requireNonNull(livro, "Livro não pode ser nulo");
        this.dataEmprestimo = Objects.requireNonNull(dataEmprestimo, "Data de empréstimo não pode ser nula");
        this.dataDevolucaoPrevista = Objects.requireNonNull(dataDevolucaoPrevista, "Data de devolução prevista não pode ser nula");
        this.statusEmprestimo = Objects.requireNonNull(status, "Status do empréstimo não pode ser nulo");
        this.diasAtrasados = 0;
        this.qtdRenovado = 0;
    }

    // Construtor para frameworks (Jackson, Hibernate)
    protected Emprestimo() {}

    public static Emprestimo criar(Aluno aluno, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        return new Emprestimo(null, aluno, livro, dataEmprestimo, dataDevolucaoPrevista, StatusEmprestimo.ATIVO);
    }

    public static Emprestimo criarComId(Long id, Aluno aluno, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, int diasAtrasados, int qtdRenovado, StatusEmprestimo status) {
        Emprestimo emprestimo = new Emprestimo(id, aluno, livro, dataEmprestimo, dataDevolucaoPrevista, status);
        emprestimo.diasAtrasados = diasAtrasados;
        emprestimo.qtdRenovado = qtdRenovado;
        return emprestimo;
    }

    public int getDiasAtrasadosCalculado() {
        if (LocalDate.now().isAfter(this.dataDevolucaoPrevista)) {
            return (int) (LocalDate.now().toEpochDay() - this.dataDevolucaoPrevista.toEpochDay());
        }
        return 0;
    }

    public void arquivar() {
        this.statusEmprestimo = StatusEmprestimo.ARQUIVADO;
    }

    public void renovar(int diasParaAdicionar) {
        this.dataDevolucaoPrevista = this.dataDevolucaoPrevista.plusDays(diasParaAdicionar);
        this.statusEmprestimo = StatusEmprestimo.ATIVO;
        this.diasAtrasados = 0;
        this.qtdRenovado++;
    }

    public void finalizar() {
        this.statusEmprestimo = StatusEmprestimo.FINALIZADO;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public int getQtdRenovado() {
        return qtdRenovado;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }
}