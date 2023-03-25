package ru.kirill.cuturl.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kirill.cuturl.dto.ExceptionResponse;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(RuntimeException exception,
                                                    HttpServletRequest httpServletRequest) {
        ExceptionResponse response = getExceptionResponse(exception, httpServletRequest);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private static ExceptionResponse getExceptionResponse(RuntimeException exception,
                                                          HttpServletRequest httpServletRequest) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .path(httpServletRequest.getServletPath())
                .build();
    }
}
