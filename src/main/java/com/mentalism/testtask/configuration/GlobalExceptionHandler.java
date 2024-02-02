package com.mentalism.testtask.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Универсальный обработчик
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> catchSomeException(Exception e) {
        log.error(
                String.format(
                """
                Ошибка типа Exception была перехвачена.
                Причина: %s
                Сообщение %s
                """,
                        e.getMessage(), e.getMessage()
                ));

        return ResponseEntity.badRequest().build();
    }
}
