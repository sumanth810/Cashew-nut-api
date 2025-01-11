package in.koyya.cashew.erp;

import lombok.Getter;
import lombok.Setter;

/**
 * Model representing an authentication response containing the JWT token.
 */
@Getter
@Setter
public class AuthResponse {
    private String jwt;

    /**
     * Constructor to initialize AuthResponse with a JWT token.
     *
     * @param jwt The JWT token
     */
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
