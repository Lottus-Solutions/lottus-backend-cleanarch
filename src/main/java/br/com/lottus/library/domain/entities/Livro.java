package br.com.lottus.library.domain.entities;

import br.com.lottus.library.domain.exceptions.*;
import br.com.lottus.library.domain.entities.Categoria;

import java.util.Objects;

public class Livro {
    private Long id;
    private String nome;
    private String autor;
    private Integer quantidade;
    private Integer quantidadeDisponivel;
    private StatusLivro status;
    private String descricao;
    private Categoria categoria;

    private Livro(Long id, String nome, String autor, Categoria categoria, Integer quantidade, Integer quantidadeDisponivel, StatusLivro status, String descricao) {
        this.id = id;
        this.nome = validarNome(nome);
        this.autor = validarAutor(autor);
        this.categoria = Objects.requireNonNull(categoria, "Categoria não pode ser nula");
        this.quantidade = validarQuantidade(quantidade);
        this.quantidadeDisponivel = validarQuantidadeDisponivel(quantidadeDisponivel, quantidade);
        this.status = Objects.requireNonNull(status, "Status do livro não pode ser nulo");
        this.descricao = validarDescricao(descricao);
    }

    public static Livro criar(String nome, String autor, Categoria categoria, Integer quantidade, StatusLivro status, String descricao) {
        return new Livro(null, nome, autor, categoria, quantidade, quantidade, status, descricao);
    }

    public static Livro criarComId(Long id, String nome, String autor, Categoria categoria, Integer quantidade, Integer quantidadeDisponivel, StatusLivro status, String descricao) {
        return new Livro(id, nome, autor, categoria, quantidade, quantidadeDisponivel, status, descricao);
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new NomeLivroVazioOuNuloException();
        }
        return nome;
    }

    private static String validarAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            throw new AutorLivroVazioOuNuloException();
        }
        return autor;
    }

    private static Integer validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade < 1) {
            throw new QuantidadeLivroInvalidaException();
        }
        return quantidade;
    }

    private static Integer validarQuantidadeDisponivel(Integer quantidadeDisponivel, Integer quantidadeTotal) {
        if (quantidadeDisponivel == null || quantidadeDisponivel < 0 || quantidadeDisponivel > quantidadeTotal) {
            throw new QuantidadeDisponivelInvalidaException();
        }
        return quantidadeDisponivel;
    }

    private static String validarDescricao(String descricao) {
        if (descricao != null && descricao.length() > 500) {
            throw new DescricaoLivroMuitoLongaException();
        }
        return descricao;
    }

    public void emprestar() {
        if (this.quantidadeDisponivel == 0) {
            throw new br.com.lottus.library.application.exceptions.LivroIndisponivelException();
        }
        this.quantidadeDisponivel--;
        if (this.quantidadeDisponivel == 0) {
            this.status = StatusLivro.RESERVADO;
        }
    }

    public void devolver() {
        if (this.quantidadeDisponivel < this.quantidade) {
            this.quantidadeDisponivel++;
            if (this.status == StatusLivro.RESERVADO) {
                this.status = StatusLivro.DISPONIVEL;
            }
        }
    }

    public void alterarNome(String nome) { this.nome = validarNome(nome); }
    public void alterarAutor(String autor) { this.autor = validarAutor(autor); }
    public void alterarCategoria(Categoria categoria) { this.categoria = Objects.requireNonNull(categoria); }
    public void alterarQuantidade(Integer quantidade) {
        this.quantidade = validarQuantidade(quantidade);
        if (this.quantidadeDisponivel > quantidade) {
            this.quantidadeDisponivel = quantidade;
        }
    }
    public void alterarQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = validarQuantidadeDisponivel(quantidadeDisponivel, this.quantidade);
    }
    public void alterarStatus(StatusLivro status) { this.status = Objects.requireNonNull(status); }
    public void alterarDescricao(String descricao) { this.descricao = validarDescricao(descricao); }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getAutor() { return autor; }
    public Integer getQuantidade() { return quantidade; }
    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public StatusLivro getStatus() { return status; }
    public String getDescricao() { return descricao; }
    public Categoria getCategoria() { return categoria; }

}
