package br.com.bank.bank.gateway.bankgatewayapi.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/extract/**")
                        .uri("http://localhost:8082/")
                        .id("extractApi"))

                .route(r -> r.path("/bank/**")
                        .uri("http://localhost:8081/")
                        .id("bankApi"))
                .build();
    }
}