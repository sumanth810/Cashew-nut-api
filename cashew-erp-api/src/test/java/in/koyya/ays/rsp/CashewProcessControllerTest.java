package in.koyya.ays.rsp;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.koyya.cashew.erp.BatchData;
import in.koyya.cashew.erp.CashewProcessController;
import in.koyya.cashew.erp.CashewProcessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CashewProcessController.class)
public class CashewProcessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CashewProcessService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testSubmitProcessSuccess() throws Exception {
        BatchData batchData = new BatchData(1L, LocalDate.now(), 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(service.submitProcess(any(BatchData.class))).thenReturn(batchData);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cashew-process/batchdata")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(batchData)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/cashew-process/batchdata/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(LocalDate.now().toString()))
                
                .andDo(print());
    }

    @Test
    public void testGetBatchDataByDateSuccess() throws Exception {
        LocalDate date = LocalDate.now();
        BatchData batchData = new BatchData(1L, date, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(service.getBatchDataByDate(date)).thenReturn(Arrays.asList(batchData));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cashew-process/batchdata")
                .param("date", date.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(date.toString()))
                .andDo(print());
    }

    @Test
    public void testGetBatchDataByDateNotFound() throws Exception {
        LocalDate date = LocalDate.now();
        when(service.getBatchDataByDate(date)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cashew-process/batchdata")
                .param("date", date.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }
}
