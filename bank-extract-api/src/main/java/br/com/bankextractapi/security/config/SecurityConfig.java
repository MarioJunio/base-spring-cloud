package br.com.bankextractapi.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import property.JwtConfiguration;
import security.AuthTokenParser;
import security.config.SecurityTokenConfig;
import security.filter.TokenAuthorizationFilter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {

    private JwtConfiguration jwtConfiguration;
    private AuthTokenParser authTokenParser;

    @Autowired
    public SecurityConfig(JwtConfiguration jwtConfiguration, AuthTokenParser authTokenParser) {
        this.jwtConfiguration = jwtConfiguration;
        this.authTokenParser = authTokenParser;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.addFilterAfter(new TokenAuthorizationFilter(jwtConfiguration, authTokenParser), UsernamePasswordAuthenticationFilter.class);
    }
}
