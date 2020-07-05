package br.com.gateway.bankgatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import property.JwtConfiguration;
import security.AuthToken;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"model"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan(basePackageClasses = {BankGatewayApiApplication.class, AuthToken.class, })
public class BankGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankGatewayApiApplication.class, args);
	}

}
