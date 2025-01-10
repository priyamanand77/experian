package com.date.add_days.exception;

import com.date.add_days.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionalHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GenericResponse> handleCustomException(CustomException customException) {
        GenericResponse genericResponse = GenericResponse.builder()
                .statusCode(customException.getHttpStatus().value())
                .message(customException.getMessage())
                .data(customException.getCause())
                .build();

        return ResponseEntity.status(customException.getHttpStatus().value())
                .body(genericResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationFailedExceptionHandler(MethodArgumentNotValidException notValidException) {
        Map<String, String> errorMap = new HashMap<>();
        notValidException.getBindingResult().getFieldErrors()
                .forEach(e -> errorMap.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(errorMap);
    }


}
