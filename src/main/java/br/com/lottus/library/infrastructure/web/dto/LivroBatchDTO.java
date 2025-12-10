package br.com.lottus.library.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroBatchDTO {
    private String nome;
    private String autor;
    private Integer quantidade;
    private Long categoriaId;
    private String descricao;
}
