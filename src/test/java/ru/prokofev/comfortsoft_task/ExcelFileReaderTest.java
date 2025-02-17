package ru.prokofev.comfortsoft_task;

import org.junit.jupiter.api.Test;
import ru.prokofev.comfortsoft_task.util.ExcelFileReader;
import ru.prokofev.comfortsoft_task.util.FileProcessingException;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelFileReaderTest {

    @Test
    void testReadNumbers() throws FileProcessingException {
        String filePath = "src/test/resources/test.xlsx";
        int[] numbers = ExcelFileReader.readNumbers(filePath);

        assertNotNull(numbers);
        assertEquals(5, numbers.length);
        assertArrayEquals(new int[]{10, 20, 30, 40, 50}, numbers);
    }

    @Test
    void testReadNumbers_FileNotFound() {
        String filePath = "src/test/resources/non_existent_file.xlsx";
        Exception exception = assertThrows(FileProcessingException.class, () -> {
            ExcelFileReader.readNumbers(filePath);
        });
        assertEquals("Файл по пути \"" + filePath + "\" не найден", exception.getMessage());
    }

    @Test
    void testReadNumbers_InvalidData() {
        String filePath = "src/test/resources/invalid_data.xlsx";
        Exception exception = assertThrows(FileProcessingException.class, () -> {
            ExcelFileReader.readNumbers(filePath);
        });
        assertTrue(exception.getMessage().startsWith("Ошибка чтения файла: Некорректные данные в строке "));
    }
}
