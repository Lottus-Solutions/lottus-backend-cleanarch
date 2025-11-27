package br.com.lottus.library.domain.entities;

import br.com.lottus.library.domain.exceptions.FinalidadeNaoInformadaException;

import java.io.Serializable;
import java.util.Objects;

public class JobPayload implements Serializable {
    private final String filePath;
    private final String finalidade;
    private final String token;

    private JobPayload(String filePath, String finalidade, String token) {
        this.filePath = Objects.requireNonNull(filePath, "O caminho do arquivo não pode ser nulo.");
        this.finalidade = validarFinalidade(finalidade);
        this.token = Objects.requireNonNull(token, "O token não pode ser nulo.");
    }

    public static JobPayload criar(String filePath, String finalidade, String token) {
        return new JobPayload(filePath, finalidade, token);
    }

    private String validarFinalidade(String finalidade) {
        if (finalidade == null || finalidade.isBlank()) {
            throw new FinalidadeNaoInformadaException();
        }
        return finalidade;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public String getToken() {
        return token;
    }
}