package com.mentalism.testtask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentalism.testtask.service.StringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/string")
@Slf4j
@AllArgsConstructor
public class StringController {

    private ObjectMapper objectMapper;
    private StringService stringService;

    @Operation(
            summary = "Принимает на вход строку и возвращает количество символов в строке в порядке убывания.",
            description = "Входные данные имеют формат строки.",
            parameters = {
                    @Parameter(name = "processingString", description = "Строка для обработки.", required = true, example = "аааббввфыыыыку")
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Запрос успешен",
            content = @Content(examples = {
                    @ExampleObject(value = """
                            {
                              "ы": 4,
                              "а": 3,
                              "б": 2,
                              "в": 2,
                              "ф": 1,
                              "к": 1,
                              "у": 1
                            }
                            """)
            })
    )
    @ApiResponse(
            responseCode = "403",
            description = "Неверный формат входных данных.",
            content = @Content(examples = {
                    @ExampleObject(value = "403 BAD REQUEST")
            })
    )
    @GetMapping("/char-count")
    public ResponseEntity<String> getCharsCount(@RequestParam(required = true) String processingString) throws JsonProcessingException {
        System.out.println(stringService.calculateCharCount(processingString));
        return ResponseEntity.ok().body(objectMapper.writeValueAsString(stringService.calculateCharCount(processingString)));
    }
}
