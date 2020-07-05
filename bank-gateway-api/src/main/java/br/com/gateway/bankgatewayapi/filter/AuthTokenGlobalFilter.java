package br.com.gateway.bankgatewayapi.filter;

import com.nimbusds.jose.JOSEException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import security.AuthToken;
import security.AuthTokenParser;

import java.text.ParseException;
import java.util.Optional;

@Slf4j
@Component
public class AuthTokenGlobalFilter extends AbstractGatewayFilterFactory<AuthTokenGlobalFilter.Config> {

    private AuthTokenParser authTokenParser;

    @Autowired
    public AuthTokenGlobalFilter(AuthTokenParser authTokenParser) {
        super(Config.class);
        this.authTokenParser = authTokenParser;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {
            Optional<String> token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).stream().findFirst();

            if (token.isPresent()) {
                log.info("Token de autorização está presente, decifrando...");

                try {
                    String jws = this.authTokenParser.uncipher(token.get());
                    ServerHttpRequest mutateRequest = exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, jws).build();

                    return chain.filter(exchange.mutate().request(mutateRequest).build());
                } catch (ParseException | JOSEException e) {
                    log.info("Erro ao decifrar token '{}'", token);
                    e.printStackTrace();

                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

            } else
                log.info("Token de autorização não está presente!!!");

            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String name;
    }
}
