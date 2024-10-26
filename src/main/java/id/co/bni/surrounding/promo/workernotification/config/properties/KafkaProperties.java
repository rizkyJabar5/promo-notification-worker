package id.co.bni.surrounding.promo.workernotification.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;

@ConfigurationProperties("spring.kafka")
public record KafkaProperties(
        String bootstrapServers,
        ProducerProperties producer,
        String topic) {

    public record ProducerProperties(
            String acks,
            Integer batchSize,
            String clientId,
            String keySerializer,
            String valueSerializer,
            Integer retries,
            Integer retryBackoffMs,
            Integer requestTimeoutMs,
            Integer maxBlockMs,
            @DefaultValue("false")
            Boolean jaas,
            Map<String, String> properties) {

    }
}
