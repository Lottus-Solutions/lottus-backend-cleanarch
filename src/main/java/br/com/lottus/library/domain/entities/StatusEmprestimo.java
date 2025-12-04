package br.com.lottus.library.domain.entities;

import java.io.Serializable;

public enum StatusEmprestimo implements Serializable {
    ATIVO,
    DEVOLVIDO,
    ATRASADO,
    FINALIZADO,
    ARQUIVADO
}
