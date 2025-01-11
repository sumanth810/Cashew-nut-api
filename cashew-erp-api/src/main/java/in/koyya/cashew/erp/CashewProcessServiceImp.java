package in.koyya.cashew.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of CashewProcessService interface.
 */
@Service
public class CashewProcessServiceImp implements CashewProcessService {

    // Logger for logging service activities
    private static final Logger logger = LoggerFactory.getLogger(CashewProcessServiceImp.class);

    @Autowired
    private CashewProcessRepository repository;

    /**
     * Submits and saves batch data.
     *
     * @param batchData The batch data to submit
     * @return The saved BatchData entity
     */
    @Override
    public BatchData submitProcess(BatchData batchData) {
        logger.info("Submitting process data: {}", batchData);
        if (batchData == null) {
            logger.error("BatchData is null");
            throw new IllegalArgumentException("BatchData cannot be null");
        }
        // Save batch data to the repository
        BatchData savedData = repository.save(batchData);
        logger.info("Process data saved successfully with ID: {}", savedData.getId());
        return savedData;
    }

    /**
     * Retrieves batch data for a given date.
     *
     * @param date The date for which to retrieve batch data
     * @return List of BatchData entries
     */
    @Override
    public List<BatchData> getBatchDataByDate(LocalDate date) {
        logger.info("Fetching batch data for date: {}", date);
        if (date == null) {
            logger.error("Date is null");
            throw new IllegalArgumentException("Date cannot be null");
        }
        // Retrieve batch data from the repository
        List<BatchData> batchDataList = repository.findByDate(date);
        if (batchDataList.isEmpty()) {
            logger.warn("No batch data found for date: {}", date);
        } else {
            logger.info("Batch data retrieved successfully for date: {}", date);
        }
        return batchDataList;
    }
}
