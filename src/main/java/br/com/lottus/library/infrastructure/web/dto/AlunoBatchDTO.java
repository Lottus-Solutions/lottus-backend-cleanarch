package br.com.lottus.library.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoBatchDTO {
    private String nome;
    private Double qtdBonus;
    private Integer qtdLivrosLidos;
    private Long turmaId;
}
