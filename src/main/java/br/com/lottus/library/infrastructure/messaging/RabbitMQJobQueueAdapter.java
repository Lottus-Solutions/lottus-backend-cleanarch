package br.com.lottus.library.infrastructure.messaging;

import br.com.lottus.library.application.ports.out.JobQueuePort;
import br.com.lottus.library.domain.entities.JobPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQJobQueueAdapter implements JobQueuePort {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void publish(JobPayload payload) {
        rabbitTemplate.convertAndSend(queue.getName(), payload);
    }
}
