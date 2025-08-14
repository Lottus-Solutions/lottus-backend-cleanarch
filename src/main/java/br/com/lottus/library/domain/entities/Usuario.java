package br.com.lottus.library.domain.entities;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataRegistro;
    private int idAvatar;

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, LocalDate dataRegistro, int idAvatar) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataRegistro = dataRegistro;
        this.idAvatar = idAvatar;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public int getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(int idAvatar) {
        this.idAvatar = idAvatar;
    }
}
