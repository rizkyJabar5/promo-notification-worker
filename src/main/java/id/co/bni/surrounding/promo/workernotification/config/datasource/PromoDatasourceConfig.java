package id.co.bni.surrounding.promo.workernotification.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "id.co.bni.surrounding.promo.workernotification.additional.repository.promo",
        transactionManagerRef = "promoTransactionManager",
        entityManagerFactoryRef = "promoEntityManager"
)
public class PromoDatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.promo")
    DataSourceProperties promoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.promo.hikari")
    DataSource promoDataSource(@Qualifier("promoDataSourceProperties") DataSourceProperties props) {
        return props
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean promoEntityManager(@Qualifier("promoDataSource") DataSource dataSource,
                                                              EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("id.co.bni.surrounding.promo.workernotification.additional.entity.promo")
                .build();
    }

    @Primary
    @Bean
    PlatformTransactionManager promoTransactionManager(@Qualifier("promoEntityManager") LocalContainerEntityManagerFactoryBean promoEntityManager) {
        return new JpaTransactionManager(Objects.requireNonNull(promoEntityManager.getObject()));
    }
}
