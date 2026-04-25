package com.bistral.app.bistral_auth_service.service.implementaions;

import com.bistral.app.bistral_auth_service.dtos.LoginContext;
import com.bistral.app.bistral_auth_service.entity.UserEntity;
import com.bistral.app.bistral_auth_service.service.interfaces.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of JwtService responsible for JWT generation and key management.
 *
 * Loads RSA private and public keys from configured resource paths and uses them
 * to sign JWT access and refresh tokens.
 *
 * Key initialization is performed once at application startup.
 */
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final ResourceLoader resourceLoader;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    @Value("${jwt.private.key.path}")
    private String jwtPrivateKeyPath;

    @Value("${jwt.public.key.path}")
    private String jwtPublicKeyPath;


    /**
     * Initializes RSA keys after bean creation.
     *
     * Loads private and public keys from configured file paths and caches them
     * for reuse during token generation and validation.
     */
    @PostConstruct
    public void init() throws Exception {
        this.privateKey = getPrivateKey();
        this.publicKey = getPublicKey();
    }

    /**
     * Loads and parses the RSA private key from a PEM file.
     *
     * Removes PEM headers/footers and decodes Base64 content into a PrivateKey instance.
     *
     * @return RSA PrivateKey
     * @throws Exception if key loading or parsing fails
     */
    private PrivateKey getPrivateKey() throws Exception {
        Resource resource = resourceLoader.getResource(jwtPrivateKeyPath);
        String keyBytes = new String(resource.getInputStream().readAllBytes())
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        ;
        byte[] decodedKey = Base64.getDecoder().decode(keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }

    /**
     * Loads and parses the RSA public key from a PEM file.
     *
     * Removes PEM headers/footers and decodes Base64 content into a PublicKey instance.
     *
     * @return RSA PublicKey
     * @throws Exception if key loading or parsing fails
     */
    public PublicKey getPublicKey() throws Exception {
        Resource resource = resourceLoader.getResource(jwtPublicKeyPath);
        String keyBytes = new String(resource.getInputStream().readAllBytes())
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodedKey = Base64.getDecoder().decode(keyBytes);

        X509EncodedKeySpec spec = new X509EncodedKeySpec (decodedKey);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /**
     * Generates a JWT access token for the given user.
     *
     * Includes user-specific claims such as username and user type,
     * and signs the token using the RSA private key.
     *
     * @param user authenticated user entity
     * @return signed JWT access token
     */
    @Override
    public String getAccessToken(UserEntity user, LoginContext loginContext) throws Exception {
        Objects.requireNonNull(loginContext,"login Context Cant not be null");
        return
                Jwts
                        .builder()
                        .subject(user.getUserId().toString())
                        .claim("userName", user.getUsername())
                        .claim("userType", user.getUserTypeEnum())
                        .claim("bistroId", Optional.ofNullable(loginContext
                                .getBistroId())
                                .map(UUID::toString)
                                .orElse(null))
                        .claim("branchId",
                                Optional.ofNullable(loginContext.getBranchId())
                                        .map(UUID::toString)
                                        .orElse(null))
                        .claim("Permissions",Optional
                                .ofNullable(loginContext.getPermission())
                                .orElse(List.of())
                        )
                        .signWith(this.privateKey)
                        .compact();


    }


    /**
     * Generates a JWT refresh token for the given user.
     *
     * Contains minimal user claims and is signed using the RSA private key.
     *
     * @param user authenticated user entity
     * @return signed JWT refresh token
     */
    @Override
    public String getRefreshToken(UserEntity user) throws Exception {
        return Jwts
                .builder()
                .subject(user.getUserId().toString())
                .claim("userName", user.getUsername())
                .claim("userType", user.getUserTypeEnum())
                .signWith(this.privateKey)
                .compact();

    }

    @Override
    public boolean isTokenValid(String token, UserEntity userEntity) {
        return false;
    }

    @Override
    public String getUserName(String token) {
        return getClaims(token).get("username",String.class);
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(this.publicKey)
                .build().parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public UUID getUserId(String token) {
        return UUID.fromString(getClaims(token).getSubject());
    }

    @Override
    public UUID getBistroId(String token) {
        return getClaims(token).get("bistroId",UUID.class);
    }

    @Override
    public UUID getBranchId(String token) {
        return  getClaims(token).get("bistroId",UUID.class);
    }

    @Override
    public List<String> getPermissions(String token) {
        return getClaims(token).get("permission",List.class);

    }


}
