package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.EnfileirarProcessamentoArquivoCommand;

public interface EnfileirarProcessamentoArquivoUseCase {
    void executar(EnfileirarProcessamentoArquivoCommand command);
}
