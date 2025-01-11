package in.koyya.cashew.erp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Custom implementation of UserDetailsService to load user-specific data.
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Loads the user details by username (email).
     *
     * @param email The email of the user
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch Admin entity by email
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            log.error("Admin not found with email: {}", email);
            throw new UsernameNotFoundException("Admin not found with email: " + email);
        }
        log.info("Admin found with email: {}", email);
        // Return UserDetails object with email and password
        return new User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
    }
}
