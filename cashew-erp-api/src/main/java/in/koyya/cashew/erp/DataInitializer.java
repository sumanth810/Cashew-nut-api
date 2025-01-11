package in.koyya.cashew.erp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Component to initialize data on application startup.
 */
@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Runs on application startup to initialize data.
     *
     * @param args Command-line arguments
     * @throws Exception If an error occurs during initialization
     */
    @Override
    public void run(String... args) throws Exception {
        // Check if admin repository is empty
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setEmail("admin@cashew.com");
            // Encode the password before saving
            admin.setPassword(passwordEncoder.encode("admin123"));
            // Save the admin user to the repository
            adminRepository.save(admin);
            log.info("Admin user created with email: {}", admin.getEmail());
        }
    }
}
