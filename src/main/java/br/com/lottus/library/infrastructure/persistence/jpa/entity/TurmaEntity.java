package br.com.lottus.library.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "turma")
@Data
public class TurmaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
