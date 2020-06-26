package br.com.gateway.bankgatewayapi.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class BankPostFilter extends AbstractGatewayFilterFactory<BankPostFilter.Config> {

    public BankPostFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            final ServerHttpResponse response = exchange.getResponse();

            response.getHeaders().forEach((Object k, Object v) -> {
                System.out.printf("%s: %s\n", k, v);
            });
        }));
    }

    @Data
    public static class Config {
        private String name;
    }
}
