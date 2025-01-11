package in.koyya.cashew.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Cashew ERP Spring Boot application.
 */
@SpringBootApplication
public class CashewERPApplication {

    // Logger for logging application events
    public static Logger logger = LoggerFactory.getLogger(CashewERPApplication.class);

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CashewERPApplication.class, args);
        logger.info("Cashew ERP Application Started Successfully!");
    }
}
