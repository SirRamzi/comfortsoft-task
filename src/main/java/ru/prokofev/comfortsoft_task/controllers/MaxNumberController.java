package ru.prokofev.comfortsoft_task.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition
public class MaxNumberController {

    @PostMapping("/findNthMax")
    public ResponseEntity<?> findNthMax(
            @RequestParam String filePath,
            @RequestParam int n
    ) {
        return null;
    }
}
