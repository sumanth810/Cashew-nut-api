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
    private CashewProcessRepository repository;

    @InjectMocks
    private CashewProcessServiceImp service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitProcessSuccess() {
        BatchData batchData = new BatchData(1L, LocalDate.now(), 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(repository.save(any(BatchData.class))).thenReturn(batchData);

        BatchData result = service.submitProcess(batchData);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(BatchData.class));
    }

    @Test
    public void testGetBatchDataByDateSuccess() {
        LocalDate date = LocalDate.now();
        BatchData batchData = new BatchData(1L, date, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(repository.findByDate(date)).thenReturn(Arrays.asList(batchData));

        List<BatchData> result = service.getBatchDataByDate(date);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(repository, times(1)).findByDate(date);
    }

    @Test
    public void testGetBatchDataByDateNotFound() {
        LocalDate date = LocalDate.now();
        when(repository.findByDate(date)).thenReturn(Arrays.asList());

        List<BatchData> result = service.getBatchDataByDate(date);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findByDate(date);
    }
}

