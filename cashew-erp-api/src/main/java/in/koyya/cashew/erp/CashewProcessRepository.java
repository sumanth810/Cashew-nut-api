package in.koyya.cashew.erp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing BatchData entities.
 */
@Repository
public interface CashewProcessRepository extends JpaRepository<BatchData, Long> {
	
    /**
     * Finds a list of BatchData entries by date.
     *
     * @param date The date for which to find BatchData
     * @return List of BatchData entries matching the date
     */
    List<BatchData> findByDate(LocalDate date);
}
