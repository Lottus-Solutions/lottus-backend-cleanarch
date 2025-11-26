package br.com.lottus.library.application.ports.out;

import br.com.lottus.library.domain.entities.JobPayload;

public interface JobQueuePort {
    void publish(JobPayload payload);
}
