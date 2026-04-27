package dev.twotough.springlab.tcgstore.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:mySecretKeyThatIsAtLeast256BitsLongForHS256Algorithm}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Genera token con userId como subject y username como claim extra
    public String generateToken(Long userId, String username) {
        return Jwts.builder()
                .subject(userId.toString())
                .claim("username", username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    // Extrae el userId del token
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    // Extrae el username del token (sin ir a BD)
    public String getUsernameFromToken(String token) {
        return getClaims(token).get("username", String.class);
    }

    // Valida firma y expiración
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Privado — descifra el token y devuelve el contenido
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}