package in.koyya.cashew.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for handling cashew processing batch data.
 */
@RestController
@RequestMapping("/api/cashew-process")
@Validated
public class CashewProcessController {

    // Logger for logging controller activities
    private static final Logger logger = LoggerFactory.getLogger(CashewProcessController.class);

    @Autowired
    private CashewProcessService service;

    /**
     * Endpoint to submit batch data for processing.
     *
     * @param batchData The batch data to be submitted
     * @return ResponseEntity with the saved BatchData and HTTP status
     */
    @PostMapping("/batchdata")
    public ResponseEntity<BatchData> submitProcess(@Valid @RequestBody BatchData batchData) {
        logger.info("Received request to submit batch data: {}", batchData);
        try {
            // Save the batch data using the service
            BatchData savedProcess = service.submitProcess(batchData);

            // Create URI for the newly created resource
            URI location = new URI("/api/cashew-process/batchdata/" + savedProcess.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            logger.info("Batch data submitted successfully with ID: {}", savedProcess.getId());
            // Return 201 Created with the saved BatchData
            return ResponseEntity.created(location).body(savedProcess);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid batch data: {}", e.getMessage());
            // Return 400 Bad Request for invalid data
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (URISyntaxException e) {
            logger.error("Error in URI syntax: {}", e.getMessage());
            // Return 500 Internal Server Error for URI issues
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Return 500 Internal Server Error for any other exceptions
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to retrieve batch data by a specific date.
     *
     * @param date The date for which to retrieve batch data
     * @return ResponseEntity with the list of BatchData and HTTP status
     */
    @GetMapping("/batchdata")
    public ResponseEntity<List<BatchData>> getBatchDataByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Received request to fetch batch data for date: {}", date);
        try {
            // Fetch batch data using the service
            List<BatchData> batchDataList = service.getBatchDataByDate(date);
            if (batchDataList.isEmpty()) {
                logger.warn("No batch data found for date: {}", date);
                // Return 404 Not Found if no data exists for the given date
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.info("Batch data retrieved successfully for date: {}", date);
            // Return 200 OK with the list of BatchData
            return new ResponseEntity<>(batchDataList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid date provided: {}", e.getMessage());
            // Return 400 Bad Request for invalid date
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Return 500 Internal Server Error for any other exceptions
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
