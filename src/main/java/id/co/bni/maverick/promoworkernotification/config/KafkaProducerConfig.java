package id.co.bni.maverick.promoworkernotification.config;

import id.co.bni.maverick.promoworkernotification.config.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.MAX_BLOCK_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRY_BACKOFF_MS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

@EnableConfigurationProperties({KafkaProperties.class})
@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(this.configProps());
    }

    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    private HashMap<String, Object> configProps() {
        var properties = new HashMap<String, Object>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, this.kafkaProperties.bootstrapServers());
        properties.put(ACKS_CONFIG, this.kafkaProperties.producer().acks());
        properties.put(BATCH_SIZE_CONFIG, this.kafkaProperties.producer().batchSize());
        properties.put(CLIENT_ID_CONFIG, this.kafkaProperties.producer().clientId());
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, this.kafkaProperties.producer().keySerializer());
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, this.kafkaProperties.producer().valueSerializer());
        properties.put(RETRIES_CONFIG, this.kafkaProperties.producer().retries());
        properties.put(RETRY_BACKOFF_MS_CONFIG, this.kafkaProperties.producer().retryBackoffMs());
        properties.put(REQUEST_TIMEOUT_MS_CONFIG, this.kafkaProperties.producer().requestTimeoutMs());
        properties.put(MAX_BLOCK_MS_CONFIG, this.kafkaProperties.producer().maxBlockMs());

        if (this.kafkaProperties.producer().jaas()) {
            properties.putAll(this.kafkaProperties.producer().properties());
        }

        return properties;
    }
}

