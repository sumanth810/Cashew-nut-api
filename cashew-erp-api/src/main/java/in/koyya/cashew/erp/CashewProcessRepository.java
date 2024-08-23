package in.koyya.cashew.erp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashewProcessRepository extends JpaRepository<BatchData, Long> {
	
	List<BatchData> findByDate(LocalDate date);
}
