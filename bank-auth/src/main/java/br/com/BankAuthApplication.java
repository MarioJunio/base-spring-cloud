package br.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import property.JwtConfiguration;
import security.AuthToken;

@SpringBootApplication
@EntityScan({"model"})
@EnableJpaRepositories({"repository"})
@EnableEurekaClient
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan(basePackageClasses = {BankAuthApplication.class, AuthToken.class})
public class BankAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAuthApplication.class, args);
	}

}
