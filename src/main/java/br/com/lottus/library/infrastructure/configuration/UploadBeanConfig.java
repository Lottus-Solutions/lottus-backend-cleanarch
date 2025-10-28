package br.com.lottus.library.infrastructure.configuration;

import br.com.lottus.library.application.ports.out.FileStoragePort;
import br.com.lottus.library.application.ports.out.JobQueuePort;
import br.com.lottus.library.application.usecases.EnfileirarProcessamentoArquivoUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadBeanConfig {

    @Bean
    public EnfileirarProcessamentoArquivoUseCaseImpl enfileirarProcessamentoArquivoUseCase(
            FileStoragePort fileStoragePort,
            JobQueuePort jobQueuePort) {
        return new EnfileirarProcessamentoArquivoUseCaseImpl(fileStoragePort, jobQueuePort);
    }
}
