package br.com.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import property.JwtConfiguration;
import security.AuthToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtConfiguration jwtConfiguration;
    private AuthenticationManager authenticationManager;
    private AuthToken authToken;

    @Autowired
    public JwtAuthenticationFilter(JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager, AuthToken authToken) {
        this.jwtConfiguration = jwtConfiguration;
        this.authenticationManager = authenticationManager;
        this.authToken = authToken;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = Optional.ofNullable(new ObjectMapper().readValue(request.getInputStream(), User.class)).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não informado"));

        log.info("Autenticando usuario '{}' via UserDetailsService.loadUserByUsername", user.getUsername());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
        authToken.setDetails(user);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Autenticação realizada para o usuario '{}'", authResult.getName());

        SignedJWT signedJWT = authToken.createSignedJWT(authResult);
        log.info("Token assinado foi gerado");

        String tokenCipher = authToken.cipherSignedJWT(signedJWT);
        log.info("Token assinado e cifrado '{}'", tokenCipher);

        log.info("Adicionando token ao response header");

        response.addHeader("Access-Control-Expose-Headers", String.format("%s, %s", "XSRF-TOKEN", jwtConfiguration.getHeader().getName()));
        response.addHeader(jwtConfiguration.getHeader().getName(), String.format("%s %s", jwtConfiguration.getHeader().getPrefix(), tokenCipher));

        response.setStatus(HttpStatus.CREATED.value());
    }
}
