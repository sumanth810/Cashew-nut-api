package in.koyya.cashew.erp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashewERPApplication {

	public static Logger logger = LoggerFactory.getLogger(CashewERPApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CashewERPApplication.class, args);
		logger.info("Cashew ERP Application Started Successfully!");
	}
}
