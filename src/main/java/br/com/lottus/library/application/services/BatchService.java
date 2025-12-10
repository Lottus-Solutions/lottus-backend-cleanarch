package br.com.lottus.library.application.services;

import br.com.lottus.library.application.ports.in.CadastrarAlunosEmLoteUseCase;
import br.com.lottus.library.application.ports.in.CadastrarLivrosEmLoteUseCase;
import br.com.lottus.library.infrastructure.persistence.repository.BatchRepository;
import br.com.lottus.library.infrastructure.web.dto.AlunoBatchDTO;
import br.com.lottus.library.infrastructure.web.dto.LivroBatchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService implements CadastrarLivrosEmLoteUseCase, CadastrarAlunosEmLoteUseCase {

    private final BatchRepository batchRepository;

    @Override
    public void cadastrarLivros(List<LivroBatchDTO> livros) {
        batchRepository.saveAllLivros(livros);
    }

    @Override
    public void cadastrarAlunos(List<AlunoBatchDTO> alunos) {
        batchRepository.saveAllAlunos(alunos);
    }
}
