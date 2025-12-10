package br.com.lottus.library.infrastructure.persistence.repository;

import br.com.lottus.library.infrastructure.web.dto.AlunoBatchDTO;
import br.com.lottus.library.infrastructure.web.dto.LivroBatchDTO;

import java.util.List;

public interface BatchRepository {
    void saveAllLivros(List<LivroBatchDTO> livros);
    void saveAllAlunos(List<AlunoBatchDTO> alunos);
}
