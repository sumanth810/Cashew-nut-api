package in.koyya.cashew.erp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for generating and validating JWT tokens.
 */
@Component
@Slf4j
public class JwtUtil {

    // Secret key for signing JWT tokens, injected from application properties
    @Value("${jwt.secret-key}")
    private String secretKey;

    // JWT token expiration time in milliseconds, injected from application properties
    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    /**
     * Generates a JWT token for the given email.
     *
     * @param email The email of the user
     * @return The generated JWT token
     */
    public String generateToken(String email) {
        log.info("Generating token for email: {}", email);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * Extracts the email (subject) from the JWT token.
     *
     * @param token The JWT token
     * @return The email extracted from the token
     */
    public String getEmailFromToken(String token) {
        log.info("Extracting email from token");
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token to validate
     * @return True if the token is valid, else false
     */
    public boolean validateToken(String token) {
        try {
            log.info("Validating token");
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.error("Token has expired", e);
        } catch (Exception e) {
            log.error("Token validation failed", e);
        }
        return false;
    }

    /**
     * Checks if the JWT token has expired.
     *
     * @param token The JWT token
     * @return True if expired, else false
     */
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

    /**
     * Refreshes the JWT token if it has expired.
     *
     * @param token The JWT token to refresh
     * @return The refreshed token if expired, else the original token
     */
    public String refreshToken(String token) {
        if (isTokenExpired(token)) {
            String email = getEmailFromToken(token);
            log.info("Refreshing token for email: {}", email);
            return generateToken(email);
        }
        log.info("Token is still valid, no refresh needed");
        return token;
    }
}
