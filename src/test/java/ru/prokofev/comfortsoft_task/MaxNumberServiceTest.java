package ru.prokofev.comfortsoft_task;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.prokofev.comfortsoft_task.services.MaxNumberService;
import ru.prokofev.comfortsoft_task.util.ExcelFileReader;
import ru.prokofev.comfortsoft_task.util.FileProcessingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaxNumberServiceTest {

    private MockedStatic<ExcelFileReader> excelFileReader;

    @InjectMocks
    private MaxNumberService maxNumberService;

    @BeforeEach
    public void setUp() {
        excelFileReader = mockStatic(ExcelFileReader.class);
    }

    @AfterEach
    public void tearDown() {
        excelFileReader.close();
    }

    @Test
    void testFindNthMax() throws FileProcessingException {
        int[] numbers = {10, 20, 30, 40, 50};
        excelFileReader.when(() -> ExcelFileReader.readNumbers(anyString())).thenReturn(numbers);
        int result = maxNumberService.findNthMax("some_file.xlsx", 2);
        assertEquals(40, result);
    }

    @Test
    void testFindNthMax_NExceedsSize() {
        int[] numbers = {10, 20};
        excelFileReader.when(() -> ExcelFileReader.readNumbers(anyString())).thenReturn(numbers);
        Exception exception = assertThrows(FileProcessingException.class, () -> {
            maxNumberService.findNthMax("some_file.xlsx", 3);
        });
        assertEquals("N превышает количество чисел в файле", exception.getMessage());
    }

    @Test
    void testFindNthMax_InvalidN() {
        Exception exception = assertThrows(FileProcessingException.class, () -> {
            maxNumberService.findNthMax("some_file.xlsx", 0);
        });
        assertEquals("N должно быть больше нуля", exception.getMessage());
    }
}
