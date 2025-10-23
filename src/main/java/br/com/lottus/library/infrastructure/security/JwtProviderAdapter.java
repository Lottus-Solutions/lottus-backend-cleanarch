package br.com.lottus.library.infrastructure.security;

import br.com.lottus.library.application.ports.out.JwtProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProviderAdapter implements JwtProviderPort {

    @Value("${jwt.secret:defaultSecretKeydefaultSecretKeydefaultSecretKeydefaultSecretKeydefaultSecretKeydefaultSecretKey}")
    private String secret;

    @Value("${jwt.expiration:36000}")
    private long expiration;

    private static final String AUTH_LEVEL_CLAIM = "auth_level";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(String email) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expiration);

        return Jwts.builder()
                .setSubject(email)
                .claim(AUTH_LEVEL_CLAIM, "COMPLETE")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generatePartialToken(String email){
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(180);

        return Jwts.builder()
                .setSubject(email)
                .claim(AUTH_LEVEL_CLAIM, "PARTIAL")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getAuthLevelFromToken(String token) {
        try{
            return getAllClaimsFromToken(token).get(AUTH_LEVEL_CLAIM, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new MalformedJwtException("JWT token is null or empty");
        }
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    @Override
    public String getJTIFromToken(String token) {
        return getAllClaimsFromToken(token).getId();
    }

    @Override
    public boolean isTokenValid(String token, String email) {
        try {
            final String username = getEmailFromToken(token);
            return (username.equals(email) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    @Override
    public Duration getExpirationTime(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return Duration.between(Instant.now(), expirationDate.toInstant());
    }
}
