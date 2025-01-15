package in.koyya.ays.rsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.koyya.cashew.erp.BatchData;
import in.koyya.cashew.erp.CashewProcessRepository;
import in.koyya.cashew.erp.CashewProcessServiceImp;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CashewProcessServiceImpTest {

    @Mock
    private CashewProcessRepository repository; // Mocking the repository to avoid real database calls

    @InjectMocks
    private CashewProcessServiceImp service; // Injecting the mocked repository into the service class

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initializing mocks before each test
    }

    @Test
    public void testSubmitProcessSuccess() {
        // Creating a mock BatchData object to return from the repository
        BatchData batchData = new BatchData(1L, LocalDate.now(), 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(repository.save(any(BatchData.class))).thenReturn(batchData); // Mocking the repository save method

        // Calling service method and verifying results
        BatchData result = service.submitProcess(batchData);
        assertNotNull(result); // Asserting that result is not null
        assertEquals(1L, result.getId()); // Asserting the ID is correct
        verify(repository, times(1)).save(any(BatchData.class)); // Verifying the save method was called once
    }

    @Test
    public void testGetBatchDataByDateSuccess() {
        LocalDate date = LocalDate.now();
        BatchData batchData = new BatchData(1L, date, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(repository.findByDate(date)).thenReturn(Arrays.asList(batchData)); // Mocking repository to return a list of batch data

        // Calling service method and verifying results
        List<BatchData> result = service.getBatchDataByDate(date);
        assertNotNull(result); // Asserting that result is not null
        assertEquals(1, result.size()); // Asserting the size of the result list is 1
        assertEquals(1L, result.get(0).getId()); // Verifying the ID of the first batch data
        verify(repository, times(1)).findByDate(date); // Verifying the repository method is called once
    }

    @Test
    public void testGetBatchDataByDateNotFound() {
        LocalDate date = LocalDate.now();
        when(repository.findByDate(date)).thenReturn(Arrays.asList()); // Mocking repository to return an empty list

        // Calling service method and verifying that result is empty
        List<BatchData> result = service.getBatchDataByDate(date);
        assertNotNull(result); // Asserting that result is not null
        assertTrue(result.isEmpty()); // Asserting that the result is an empty list
        verify(repository, times(1)).findByDate(date); // Verifying the repository method is called once
    }
}
