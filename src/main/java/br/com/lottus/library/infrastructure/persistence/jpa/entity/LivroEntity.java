package br.com.lottus.library.infrastructure.persistence.jpa.entity;

import br.com.lottus.library.domain.entities.Categoria;
import br.com.lottus.library.domain.entities.StatusLivro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    @Column(unique = true)
    private String nome;

    @NotBlank
    @NotEmpty
    private String autor;

    @NotNull
    @Min(value = 1, message = "É necessário ter pelo menos 1 livro para realizar o cadastro!")
    private Integer quantidade;

    @NotNull
    private Integer quantidadeDisponivel;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @Column(length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @NotEmpty String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotEmpty String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotEmpty String getAutor() {
        return autor;
    }

    public void setAutor(@NotBlank @NotEmpty String autor) {
        this.autor = autor;
    }

    public @NotNull @Min(value = 1, message = "É necessário ter pelo menos 1 livro para realizar o cadastro!") Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull @Min(value = 1, message = "É necessário ter pelo menos 1 livro para realizar o cadastro!") Integer quantidade) {
        this.quantidade = quantidade;
    }

    public @NotNull Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(@NotNull Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
