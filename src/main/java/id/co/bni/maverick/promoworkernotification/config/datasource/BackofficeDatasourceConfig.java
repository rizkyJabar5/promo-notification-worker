package id.co.bni.maverick.promoworkernotification.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "id.co.bni.maverick.promoworkernotification.additional.repository.backoffice",
        transactionManagerRef = "backofficeTransactionManager",
        entityManagerFactoryRef = "backofficeEntityManager"
)
public class BackofficeDatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.backoffice")
    DataSourceProperties backofficeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.backoffice.hikari")
    DataSource backofficeDataSource(@Qualifier("backofficeDataSourceProperties") DataSourceProperties props) {
        return props
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean backofficeEntityManager(@Qualifier("backofficeDataSource") DataSource dataSource,
                                                                   EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("id.co.bni.maverick.backofficeworkernotification.additional.entity.backoffice")
                .build();
    }

    @Bean
    PlatformTransactionManager backofficeTransactionManager(@Qualifier("backofficeEntityManager") LocalContainerEntityManagerFactoryBean backofficeEntityManager) {
        return new JpaTransactionManager(Objects.requireNonNull(backofficeEntityManager.getObject()));
    }
}
