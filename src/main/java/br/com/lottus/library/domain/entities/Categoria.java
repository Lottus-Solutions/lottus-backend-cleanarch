package br.com.lottus.library.domain.entities;

import br.com.lottus.library.domain.exceptions.CorCategoriaVazioOuNuloException;
import br.com.lottus.library.domain.exceptions.NomeCategoriaVazioOuNuloException;

public class Categoria {
    private Long id;
    private String nome;
    private String cor;

    private Categoria(Long id, String nome, String cor) {
        this.id = id;
        this.nome = validarNome(nome);
        this.cor = validarCor(cor);
    }

    public static Categoria criar(String nome, String cor) {
        String nomeValidado = validarNome(nome);
        String corValidada = validarCor(cor);

        return new Categoria(null, nomeValidado, corValidada);
    }

    public static Categoria criarComId(Long id, String nome, String cor) {
        String nomeValidado = validarNome(nome);
        String corValidada = validarCor(cor);

        return new Categoria(id, nomeValidado, corValidada);
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new NomeCategoriaVazioOuNuloException();
        }
        return nome;
    }

    private static String validarCor(String cor) {
        if (cor == null || cor.isBlank()) {
            throw new CorCategoriaVazioOuNuloException();
        }
        return cor;
    }

    public void alterarNome(String nome) { this.nome = validarNome(nome); }
    public void alterarCor(String cor) { this.cor = validarCor(cor); }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCor() { return cor; }


}
