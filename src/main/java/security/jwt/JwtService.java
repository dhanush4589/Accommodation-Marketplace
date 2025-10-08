package security.jwt;

import com.dcl.accommodate.config.AppEnv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final AppEnv appEnv;

    public JwtService(AppEnv appEnv) {
        this.appEnv = appEnv;
    }

    private static final Duration DEFAULT_TOKEN_TTL = Duration.ofDays(1);

    public String generateToken(Map<String, Object> claims, String subject, Duration ttl) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + ttl.toMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return generateToken(claims, subject, DEFAULT_TOKEN_TTL);
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    private SecretKey getSignatureKey() {
        String secret = appEnv.getJwt().getSecret();
        System.out.println("JWT_SECRET being used: " + secret); // Add this line
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
