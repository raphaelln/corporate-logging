package com.acme.logging.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.acme.logging.configuration.SparkConfig;

/**
 * @author rnascimento
 */
@ComponentScan({"com.acme.logging"})
@EnableMongoRepositories("com.acme.logging.repository")
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(SparkConfig.class)
public class CentralLoggingApplication {

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(CentralLoggingApplication.class, args);
    }

}
