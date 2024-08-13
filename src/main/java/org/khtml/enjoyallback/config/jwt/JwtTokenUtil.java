package org.khtml.enjoyallback.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access-token-expiration}")
    private long ACCESS_TOKEN_EXPIRY_TIME;

    @Value("${jwt.refresh-token-expiration}")
    private long REFRESH_TOKEN_EXPIRY_TIME;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String createAccessToken(String identifier) {
        return createToken(identifier, ACCESS_TOKEN_EXPIRY_TIME);
    }

    public String createRefreshToken(String identifier) {
        return createToken(identifier, REFRESH_TOKEN_EXPIRY_TIME);
    }

    private String createToken(String identifier, long expiryTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiryTime);
        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidateToken(String token, String identifier) {
        final String identifierInToken = extractIdentifier(token);
        return (identifierInToken.equals(identifier) && isTokenNotExpired(token));
    }

    private boolean isTokenNotExpired(String token) {
        return !Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            throw new AccessDeniedException("올바르지 않은 엑세스 토큰");
        }
    }
    public String extractIdentifier(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}