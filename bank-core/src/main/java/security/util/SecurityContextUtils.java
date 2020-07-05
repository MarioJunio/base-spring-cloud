package security.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import constants.Constants;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import security.AuthTokenParser;

import java.text.ParseException;
import java.util.List;

public class SecurityContextUtils {

    public static void setSecurityContextAuthentication(SignedJWT signedJWT) throws JOSEException, ParseException {

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        String username = claimsSet.getSubject();

        if (StringUtils.isNotEmpty(username)) {
            Long userId = claimsSet.getLongClaim(Constants.JWT_USER_ID_CLAIM);
            List<String> authorities = (List<String>) claimsSet.getClaim(Constants.JWT_AUTHORITIES_CLAIM);

            User user = User.builder().id(userId).username(username).role(String.join(",", authorities)).build();

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, AuthTokenParser.authorities(authorities));
            authToken.setDetails(signedJWT.serialize());

            SecurityContextHolder.getContext().setAuthentication(authToken);

        } else {
            throw new JOSEException("Usuário não encontrado no JWT");
        }

    }
}
