package ru.prokofev.comfortsoft_task.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.prokofev.comfortsoft_task.dto.FindNthMaxRequest;
import ru.prokofev.comfortsoft_task.services.MaxNumberService;
import ru.prokofev.comfortsoft_task.util.FileProcessingException;

@RestController
@OpenAPIDefinition
public class MaxNumberController {

    private final MaxNumberService maxNumberService;


    public MaxNumberController(MaxNumberService maxNumberService) {
        this.maxNumberService = maxNumberService;
    }

    @PostMapping("/findNthMax")
    public ResponseEntity<?> findNthMax(@RequestBody FindNthMaxRequest request) {
        if (request.getFilePath() == null || request.getN() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректный формат JSON");
        }

        try {
            int result = maxNumberService.findNthMax(request.getFilePath(), request.getN());
            return ResponseEntity.ok(result);
        } catch (FileProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка: " + e.getMessage());
        }
    }
}
