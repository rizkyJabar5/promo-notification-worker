package id.co.bni.maverick.promoworkernotification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;

@ConfigurationProperties("spring.kafka")
public record KafkaProperties(
        String bootstrapServers,
        ProducerProperties producer,
        String topic) {

    public record ProducerProperties(
            Integer acks,
            Double batchSize,
            String clientId,
            String keySerializer,
            String valueSerializer,
            Integer retries,
            Double retryBackoffMs,
            Double requestTimeoutMs,
            Double maxBlockMs,
            @DefaultValue("false")
            Boolean jaas,
            Map<String, String> properties) {

    }
}
