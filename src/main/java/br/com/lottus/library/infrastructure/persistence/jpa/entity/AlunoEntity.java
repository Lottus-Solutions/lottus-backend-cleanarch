package br.com.lottus.library.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aluno")
@Data
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    private Double qtdBonus;

    private Integer qtdLivrosLidos;

    @ManyToOne
    @JoinColumn(name = "fk_turma", nullable = false)
    private TurmaEntity turma;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmprestimoEntity> emprestimos = new ArrayList<>();
}
