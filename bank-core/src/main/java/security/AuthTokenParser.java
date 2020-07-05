package security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import property.JwtConfiguration;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthTokenParser {

    private JwtConfiguration jwtConfiguration;

    public AuthTokenParser(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public String uncipher(String tokenCipher) throws ParseException, JOSEException {
        log.info("Decifrando token '{}'", tokenCipher);

        JWEObject jweObject = JWEObject.parse(removeTokenPrefix(tokenCipher));

        DirectDecrypter directDecrypter = new DirectDecrypter(jwtConfiguration.getPrivateKey().getBytes());

        jweObject.decrypt(directDecrypter);

        String token = jweObject.getPayload().toSignedJWT().serialize();

        log.info("Token descrifrado '{}'", token);

        return token;
    }

    public void validateTokenSignature(String tokenSigned) throws ParseException, JOSEException, AccessDeniedException {
        log.info("Validando assinatura do token");

        // faz o parse do token
        SignedJWT signedJWT = SignedJWT.parse(removeTokenPrefix(tokenSigned));

        log.info("Token recuperado");

        // recupera chave pública do token para verificação
        RSAKey pubKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());

        log.info("Chave publica recuperada do token");

        if (!signedJWT.verify(new RSASSAVerifier(pubKey))) {
            throw new AccessDeniedException("Token assinado incorretamente");
        }

        log.info("Token validado");
    }

    public String removeTokenPrefix(String token) {
        return token.startsWith(jwtConfiguration.getHeader().getPrefix()) ? token.replace(jwtConfiguration.getHeader().getPrefix(), "").trim() : token.trim();
    }

    public static List<SimpleGrantedAuthority> authorities(List<String> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
    }
}
