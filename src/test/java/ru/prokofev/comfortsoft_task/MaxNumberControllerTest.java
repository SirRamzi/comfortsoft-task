package ru.prokofev.comfortsoft_task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.prokofev.comfortsoft_task.services.MaxNumberService;
import ru.prokofev.comfortsoft_task.util.FileProcessingException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MaxNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MaxNumberService maxNumberService;

    @Test
    void testFindNthMax_Success() throws Exception {
        when(maxNumberService.findNthMax(anyString(), anyInt())).thenReturn(40);

        mockMvc.perform(post("/findNthMax")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"filePath\": \"some_file.xlsx\", \"n\": 2}"))
                .andExpect(status().isOk())
                .andExpect(content().string("40"));
    }

    @Test
    void testFindNthMax_InvalidN() throws Exception {
        when(maxNumberService.findNthMax(anyString(), anyInt()))
                .thenThrow(new FileProcessingException("N должно быть больше нуля"));

        mockMvc.perform(post("/findNthMax")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"filePath\": \"some_file.xlsx\", \"n\": 0}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("N должно быть больше нуля"));
    }
}
