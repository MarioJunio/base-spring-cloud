package br.com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import property.JwtConfiguration;

@SpringBootApplication
@EntityScan({"model"})
@EnableJpaRepositories({"repository"})
@EnableEurekaClient
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class BankAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAuthApplication.class, args);
	}

}
