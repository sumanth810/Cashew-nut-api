package in.koyya.cashew.erp;

import lombok.Getter;
import lombok.Setter;

/**
 * Model representing an authentication request.
 */
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}
