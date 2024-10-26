package id.co.bni.surrounding.promo.workernotification.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Log4j2
public class PromoProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TaskExecutor publisherExecutor;

    public CompletableFuture<Void> publishAsync(String topic, Object payload) {
        return CompletableFuture
                .runAsync(this.publish(topic, payload), this.publisherExecutor)
                .whenCompleteAsync((result, err) -> {
                    if (nonNull(err)) {
                        log.error("Failed to produce event with topic {}", StringUtils.defaultString(topic), err);
                    }
                });
    }

    private Runnable publish(String topic, Object payload) {
        return () -> {
            log.info("Publishing event to Kafka topic [{}], PAYLOAD: {}", topic, payload);
            kafkaTemplate.send(topic, payload);
        };
    }
}
