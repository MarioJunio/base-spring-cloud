package br.com.gateway.bankgatewayapi.security;

import com.nimbusds.jose.JOSEException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import property.JwtConfiguration;
import reactor.core.publisher.Mono;
import security.AuthTokenParser;

import java.text.ParseException;

@Slf4j
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private AuthTokenParser authTokenParser;
    private JwtConfiguration jwtConfiguration;
    private AuthenticationManager authenticationManager;

    @Autowired
    public SecurityContextRepository(AuthTokenParser authTokenParser, JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager) {
        this.authTokenParser = authTokenParser;
        this.jwtConfiguration = jwtConfiguration;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        log.info("=> Fluxo autorização do token");

        // verifica se contem o header authorization
        if (StringUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith(jwtConfiguration.getHeader().getPrefix())) {

            try {
                // decifra o token
                String signedToken = authTokenParser.uncipher(authorizationHeader);
                Authentication authentication = new UsernamePasswordAuthenticationToken(signedToken, signedToken);

                log.info("Autorizando...");

                return authenticationManager.authenticate(authentication).map(authToken -> new SecurityContextImpl(authToken));
            } catch (ParseException | JOSEException e) {
                log.info("Erro ao decifrar token '{}'", authorizationHeader);
                e.printStackTrace();
            }
        }

        return Mono.empty();
    }
}
