package security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import constants.Constants;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import property.JwtConfiguration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthToken {

    private JwtConfiguration jwtConfiguration;

    @Autowired
    public AuthToken(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    public SignedJWT createSignedJWT(Authentication auth) throws JOSEException {
        User user = (User) auth.getPrincipal();

        // cria as claims do token
        JWTClaimsSet jwtClaimsSet = createJwtClaimsSet(user, auth);

        // gera as chaves do RSA, para cifragem e decifragem
        KeyPair rsaKeys = generateRsaKeys();

        // JWK que contem a chave pública para decifrar o token
        JWK jwk = createJWK(rsaKeys);

        // cria uma instancia do JWT
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .jwk(jwk)
                .type(JOSEObjectType.JWT)
                .build(), jwtClaimsSet);

        log.info("Assinando token com chave privada do RSA");

        RSASSASigner signer = new RSASSASigner(rsaKeys.getPrivate());
        signedJWT.sign(signer);

        log.info("Token serializado '{}'", signedJWT.serialize());

        return signedJWT;
    }

    public String cipherSignedJWT(SignedJWT signedJWT) throws JOSEException {
        log.info("Iniciando cifragem do token assinado...");

        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

        JWEObject jweObject = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                        .contentType("JWT")
                        .build(),
                new Payload(signedJWT));

        log.info("Cifrando token com a chave privada...");

        jweObject.encrypt(directEncrypter);

        log.info("Token cifrado");

        return jweObject.serialize();
    }

    private JWK createJWK(KeyPair rsaKeys) {
        log.info("Criando JWK, utilizando chave publica do RSA");
        return new RSAKey.Builder((RSAPublicKey) rsaKeys.getPublic()).keyID(UUID.randomUUID().toString()).build();
    }

    private JWTClaimsSet createJwtClaimsSet(User user, @NonNull Authentication auth) {
        log.info("Criando jwt claim set para o usuario '{}'", user);

        return new JWTClaimsSet
                .Builder()
                .subject(user.getUsername())
                .claim(Constants.JWT_USER_ID_CLAIM, user.getId())
                .claim(Constants.JWT_AUTHORITIES_CLAIM, auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .issuer("https://www.linkedin.com/in/mario-marques-martins/")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000)))
                .build();
    }

    @SneakyThrows
    private KeyPair generateRsaKeys() {
        log.info("Gerando chaves pública e privada do RSA");

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        return generator.genKeyPair();
    }
}
