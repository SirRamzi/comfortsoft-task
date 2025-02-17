package ru.prokofev.comfortsoft_task.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileReader {

    public static int[] readNumbers(String filePath) throws FileProcessingException {
        List<Integer> numbers = new ArrayList<>();
        File file = new File(filePath);

        if(!file.exists()) {
            throw new FileProcessingException("Файл по пути \"" + filePath + "\" не найден");
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell == null || cell.getCellType() != CellType.NUMERIC) {
                    throw new FileProcessingException("Некорректные данные в строке " + (row.getRowNum() + 1));
                }
                numbers.add((int) cell.getNumericCellValue());
            }
        } catch (Exception e) {
            throw new FileProcessingException("Ошибка чтения файла: " + e.getMessage());
        }

        if (numbers.isEmpty()) {
            throw new FileProcessingException("Файл пуст");
        }

        return numbers.stream().mapToInt(i -> i).toArray();
    }
}
