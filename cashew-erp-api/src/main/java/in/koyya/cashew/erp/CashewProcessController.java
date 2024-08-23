package in.koyya.cashew.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cashew-process")
@Validated
public class CashewProcessController {

    private static final Logger logger = LoggerFactory.getLogger(CashewProcessController.class);

    @Autowired
    private CashewProcessService service;

    @PostMapping("/batchdata")
    public ResponseEntity<BatchData> submitProcess(@Valid @RequestBody BatchData batchData) {
        logger.info("Received request to submit batch data: {}", batchData);
        try {
            BatchData savedProcess = service.submitProcess(batchData);

            URI location = new URI("/api/cashew-process/batchdata/" + savedProcess.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);

            logger.info("Batch data submitted successfully with ID: {}", savedProcess.getId());
            return ResponseEntity.created(location).body(savedProcess);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid batch data: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (URISyntaxException e) {
            logger.error("Error in URI syntax: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/batchdata")
    public ResponseEntity<List<BatchData>> getBatchDataByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        logger.info("Received request to fetch batch data for date: {}", date);
        try {
            List<BatchData> batchDataList = service.getBatchDataByDate(date);
            if (batchDataList.isEmpty()) {
                logger.warn("No batch data found for date: {}", date);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            logger.info("Batch data retrieved successfully for date: {}", date);
            return new ResponseEntity<>(batchDataList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid date provided: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
