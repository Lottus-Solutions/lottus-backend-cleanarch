package br.com.lottus.library.infrastructure.persistence.jpa.entity;

import br.com.lottus.library.domain.entities.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@Data
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "livro_id", nullable = false)
    private LivroEntity livro;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucaoPrevista;

    private int diasAtrasados;

    private int qtdRenovado;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo statusEmprestimo;
}
