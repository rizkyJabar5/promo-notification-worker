package id.co.bni.maverick.promoworkernotification;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ContextPropagatingTaskDecorator;

import java.time.Duration;

@Configuration
public class PublisherTaskExecutorConfig {

    @Bean
    TaskDecorator otelTaskDecorator() {
        return new ContextPropagatingTaskDecorator();
    }

    @Bean
    TaskExecutor publisherExecutor(TaskDecorator otelTaskDecorator) {
        return new ThreadPoolTaskExecutorBuilder()
                .allowCoreThreadTimeOut(Boolean.TRUE)
                .maxPoolSize(1)
                .keepAlive(Duration.ofMillis(1000))
                .threadNamePrefix("blast-promo-")
                .taskDecorator(otelTaskDecorator)
                .build();
    }
}
