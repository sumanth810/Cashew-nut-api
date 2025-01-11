package in.koyya.cashew.erp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Admin entities.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    /**
     * Finds an Admin by their email.
     *
     * @param email The email of the admin
     * @return The Admin entity if found, else null
     */
    Admin findByEmail(String email);
}
