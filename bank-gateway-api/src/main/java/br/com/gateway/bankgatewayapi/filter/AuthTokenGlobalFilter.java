package br.com.gateway.bankgatewayapi.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenGlobalFilter extends AbstractGatewayFilterFactory<AuthTokenGlobalFilter.Config> {

    public AuthTokenGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header("auth-token", "1").build();
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
