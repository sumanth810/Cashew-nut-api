package in.koyya.cashew.erp;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for handling cashew processing operations.
 */
public interface CashewProcessService {
	
    /**
     * Submits batch data for processing.
     *
     * @param batchData The batch data to submit
     * @return The saved BatchData entity
     */
    BatchData submitProcess(BatchData batchData);
	 
    /**
     * Retrieves batch data for a specific date.
     *
     * @param date The date for which to retrieve batch data
     * @return List of BatchData entries
     */
    List<BatchData> getBatchDataByDate(LocalDate date);
}
