package ru.prokofev.comfortsoft_task.services;

import org.springframework.stereotype.Service;
import ru.prokofev.comfortsoft_task.util.ExcelFileReader;
import ru.prokofev.comfortsoft_task.util.FileProcessingException;

import java.util.PriorityQueue;

@Service
public class MaxNumberService {

    public int findNthMax(String filePath, int n) throws FileProcessingException {
        if (n <= 0) {
            throw new FileProcessingException("N должно быть больше нуля");
        }

        int[] numbers = ExcelFileReader.readNumbers(filePath);

        if (numbers.length < n) {
            throw new FileProcessingException("N превышает количество чисел в файле");
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : numbers) {
            if (minHeap.size() < n) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        return minHeap.peek();
    }
}
