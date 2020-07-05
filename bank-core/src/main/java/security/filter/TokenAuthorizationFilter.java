package security.filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import property.JwtConfiguration;
import security.AuthTokenParser;
import security.util.SecurityContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private JwtConfiguration jwtConfiguration;
    private AuthTokenParser authTokenParser;

    public TokenAuthorizationFilter(JwtConfiguration jwtConfiguration, AuthTokenParser authTokenParser) {
        this.jwtConfiguration = jwtConfiguration;
        this.authTokenParser = authTokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isNotEmpty(jws)) {

            log.info("Processando token: '{}'", jws);

            try {
                authTokenParser.validateTokenSignature(jws);

                SignedJWT signedJWT = SignedJWT.parse(jws);

                SecurityContextUtils.setSecurityContextAuthentication(signedJWT);

            } catch (ParseException | JOSEException e) {
                log.info("Erro ao validar token '{}'", jws);
                e.printStackTrace();
            } finally {
                filterChain.doFilter(request, response);
            }
        }
    }
}
