package br.com.gateway.bankgatewayapi.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import property.JwtConfiguration;
import reactor.core.publisher.Mono;

@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {

    private JwtConfiguration jwtConfiguration;
    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Autowired
    public SecurityConfig(JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.jwtConfiguration = jwtConfiguration;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {

        return http
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                }))
                .and()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(new String[]{
                        jwtConfiguration.getLoginUrl()
                }).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }

}
