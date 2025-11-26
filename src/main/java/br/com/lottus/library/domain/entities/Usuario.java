package br.com.lottus.library.domain.entities;

import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;

import java.time.LocalDate;

public class Usuario {
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataRegistro;
    private Integer idAvatar;

    protected Usuario() {
    }

        private Usuario(Long id, String nome, String email, String senha, LocalDate dataRegistro, Integer idAvatar) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataRegistro = dataRegistro;
        this.idAvatar = idAvatar;
    }

    public static Usuario criar(String nome, String email, String senha, LocalDate dataRegistro, int idAvatar) {
        return new Usuario(null, nome, email, senha, dataRegistro, idAvatar);
    }

    public static Usuario criarComId(Long id, String nome, String email, String senha, LocalDate dataRegistro, Integer idAvatar) {
        return new Usuario(id, nome, email, senha, dataRegistro, idAvatar);
    }

    public static Usuario criar(CadastrarUsuarioCommand command) {
        return new Usuario(null, command.nome(), command.email(), command.senha(), command.dataRegistro(), command.idAvatar());
    }

    public void atualizarPerfil(String nome, Integer idAvatar) {
        final int TAMANHO_MINIMO_NOME = 3;
        if (nome == null || nome.trim().length() < TAMANHO_MINIMO_NOME) {
            throw new br.com.lottus.library.domain.exceptions.NomeInvalidoException("O nome deve ter pelo menos " + TAMANHO_MINIMO_NOME + " caracteres.");
        }
        if (idAvatar == null) {
            throw new br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException("O id do avatar é obrigatório.");
        }
        this.nome = nome;
        this.idAvatar = idAvatar;
    }

    //Getters and Setters
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

    public Integer getIdAvatar() {
        return idAvatar;
    }

    public void setIdAvatar(Integer idAvatar) {
        this.idAvatar = idAvatar;
    }
}
