package io.github.mouellet.flyway;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            var configuration = Flyway.configure().configuration(flyway.getConfiguration());
            Arrays.stream(flyway.getConfiguration().getSchemas())
                    .map(configuration::schemas)
                    .map(FluentConfiguration::load)
                    .forEachOrdered(Flyway::migrate);
        };
    }
}
