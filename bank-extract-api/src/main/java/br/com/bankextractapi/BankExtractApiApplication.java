package br.com.bankextractapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import property.JwtConfiguration;
import security.AuthTokenParser;
import security.filter.TokenAuthorizationFilter;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan(basePackageClasses = { BankExtractApiApplication.class, AuthTokenParser.class, TokenAuthorizationFilter.class })
public class BankExtractApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankExtractApiApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

