package br.com.lottus.library.domain.entities;

import br.com.lottus.library.domain.exceptions.NomeCategoriaVazioOuNuloException;

public class Categoria {
    private Long id;
    private String nome;
    private String cor;

    public Categoria() {}

    public Categoria(String nome, String cor) {
        validarNome(nome);
        validarCor(cor);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new NomeCategoriaVazioOuNuloException();
        } else {
            this.nome = nome;
        }
    }

    private void validarCor(String cor) {
        if (cor == null || cor.isBlank()) {
            this.cor = "#CBCBCB";
        } else {
            this.cor = cor;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
