package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.infrastructure.web.dto.AlunoBatchDTO;
import java.util.List;

public interface CadastrarAlunosEmLoteUseCase {
    void cadastrarAlunos(List<AlunoBatchDTO> alunos);
}
