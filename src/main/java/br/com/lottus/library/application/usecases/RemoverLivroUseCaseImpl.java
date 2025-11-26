package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.LivroNaoEncontradoException;
import br.com.lottus.library.application.ports.in.RemoverLivroUseCase;
import br.com.lottus.library.application.ports.out.LivroRepositoryPort;

public class RemoverLivroUseCaseImpl implements RemoverLivroUseCase {

    private final LivroRepositoryPort livroPort;

    public RemoverLivroUseCaseImpl(LivroRepositoryPort livroPort) {
        this.livroPort = livroPort;
    }

    @Override
    public void executar(Long livroId) {
        boolean existe = livroPort.findById(livroId).isPresent();
        if (!existe) {
            throw new LivroNaoEncontradoException();
        }

        livroPort.deleteById(livroId);
    }
}
