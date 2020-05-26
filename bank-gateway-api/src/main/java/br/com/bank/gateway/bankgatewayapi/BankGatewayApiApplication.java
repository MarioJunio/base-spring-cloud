package br.com.bank.gateway.bankgatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BankGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankGatewayApiApplication.class, args);
	}

}
