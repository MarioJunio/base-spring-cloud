package br.com.gateway.bankgatewayapi.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import security.AuthTokenParser;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.List;

@Slf4j
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private AuthTokenParser authTokenParser;

    @Autowired
    public AuthenticationManager(AuthTokenParser authTokenParser) {
        this.authTokenParser = authTokenParser;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String signedToken = authentication.getCredentials().toString();

        try {
            authTokenParser.validateTokenSignature(signedToken);

            SignedJWT jws = SignedJWT.parse(signedToken);
            List<SimpleGrantedAuthority> authorities = AuthTokenParser.authorities((List<String>) jws.getJWTClaimsSet().getClaim(Constants.JWT_AUTHORITIES_CLAIM));

            log.info("User '{}', Authorities: '{}'", jws.getJWTClaimsSet().getSubject(), authorities);

            return Mono.just(new UsernamePasswordAuthenticationToken(jws.getJWTClaimsSet().getSubject(), null, authorities));

        } catch (ParseException | JOSEException | AccessDeniedException e) {
            e.printStackTrace();
            return Mono.empty();
        }

    }

}
