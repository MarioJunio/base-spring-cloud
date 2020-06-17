package br.com.bank.bank.gateway.bankgatewayapi.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class BankPreFilter extends AbstractGatewayFilterFactory<BankPreFilter.Config> {

    public BankPreFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate().header("bank-security-id", String.valueOf(Math.random() * 1000)).build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
