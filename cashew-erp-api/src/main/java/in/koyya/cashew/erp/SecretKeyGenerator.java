package in.koyya.cashew.erp;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    public static void main(String[] args) {
        // Generate a 256-bit key (32 bytes)
        byte[] key = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        
        // Encode in Base64
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Secret Key: " + base64Key);
    }
}
