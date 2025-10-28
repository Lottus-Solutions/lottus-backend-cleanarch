package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.ports.command.EnfileirarProcessamentoArquivoCommand;
import br.com.lottus.library.application.ports.in.EnfileirarProcessamentoArquivoUseCase;
import br.com.lottus.library.application.ports.out.FileStoragePort;
import br.com.lottus.library.application.ports.out.JobQueuePort;
import br.com.lottus.library.domain.entities.JobPayload;

public class EnfileirarProcessamentoArquivoUseCaseImpl implements EnfileirarProcessamentoArquivoUseCase {

    private final FileStoragePort fileStoragePort;
    private final JobQueuePort jobQueuePort;

    public EnfileirarProcessamentoArquivoUseCaseImpl(FileStoragePort fileStoragePort, JobQueuePort jobQueuePort) {
        this.fileStoragePort = fileStoragePort;
        this.jobQueuePort = jobQueuePort;
    }

    @Override
    public void executar(EnfileirarProcessamentoArquivoCommand command) {
        String filePath = fileStoragePort.save(command.arquivo());

        JobPayload payload = JobPayload.criar(
                filePath,
                command.finalidade(),
                command.token()
        );

        jobQueuePort.publish(payload);
    }
}
