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
    private MockMvc mockMvc; // MockMvc is used to perform and test HTTP requests

    @MockBean
    private CashewProcessService service; // Mocking the service layer to avoid actual DB operations

    private ObjectMapper objectMapper; // Jackson object mapper to convert Java objects to JSON

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initializes mocks in the test class
        objectMapper = new ObjectMapper(); // Create ObjectMapper instance
        objectMapper.findAndRegisterModules(); // Register default modules to support features like LocalDate
    }

    @Test
    public void testSubmitProcessSuccess() throws Exception {
        // Creating a mock BatchData object to return from the service
        BatchData batchData = new BatchData(1L, LocalDate.now(), 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(service.submitProcess(any(BatchData.class))).thenReturn(batchData); // Mocking service behavior

        // Performing POST request and validating the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cashew-process/batchdata")
                .contentType(MediaType.APPLICATION_JSON) // Setting content type as JSON
                .content(objectMapper.writeValueAsString(batchData))) // Converting BatchData object to JSON
                .andExpect(MockMvcResultMatchers.status().isCreated()) // Expecting status 201 (Created)
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/cashew-process/batchdata/1")) // Verifying Location header
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) // Verifying JSON response contains correct ID
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(LocalDate.now().toString())) // Verifying Date in response
                .andDo(print()); // Printing the response for debugging
    }

    @Test
    public void testGetBatchDataByDateSuccess() throws Exception {
        LocalDate date = LocalDate.now();
        BatchData batchData = new BatchData(1L, date, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0);
        when(service.getBatchDataByDate(date)).thenReturn(Arrays.asList(batchData)); // Mocking service to return a list of batch data

        // Performing GET request and validating response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cashew-process/batchdata")
                .param("date", date.toString())) // Passing date as a parameter
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expecting status 200 (OK)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) // Verifying the first batch data entry ID
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(date.toString())) // Verifying the date
                .andDo(print()); // Printing the response for debugging
    }

    @Test
    public void testGetBatchDataByDateNotFound() throws Exception {
        LocalDate date = LocalDate.now();
        when(service.getBatchDataByDate(date)).thenReturn(Collections.emptyList()); // Mocking service to return an empty list

        // Performing GET request and verifying a 404 status if no data is found
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cashew-process/batchdata")
                .param("date", date.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound()) // Expecting status 404 (Not Found)
                .andDo(print()); // Printing the response for debugging
    }
}
