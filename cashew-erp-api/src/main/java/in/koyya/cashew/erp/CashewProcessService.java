package in.koyya.cashew.erp;

import java.time.LocalDate;
import java.util.List;

public interface CashewProcessService {
	
	 BatchData submitProcess(BatchData BatchData);
	 
	 List<BatchData> getBatchDataByDate(LocalDate date);
}
