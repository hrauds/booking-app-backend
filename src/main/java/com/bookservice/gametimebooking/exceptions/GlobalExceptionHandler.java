package com.bookservice.gametimebooking.exceptions;

import com.bookservice.gametimebooking.dto.ExceptionDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDto> handleException(UserException ex) {
        int statusCode = ex.getStatus().value();
        String errorMessage = ex.getMessage();
        return ResponseEntity
                .status(statusCode)
                .body(new ExceptionDto(statusCode, errorMessage));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> handleException(ConstraintViolationException ex) {
        int statusCode = ex.getErrorCode();
        String errorMessage = ex.getLocalizedMessage();
        return ResponseEntity
                .status(statusCode)
                .body(new ExceptionDto(statusCode, errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorMessage = "Something wrong with server, contact somebody";
        return ResponseEntity
                .status(statusCode)
                .body(new ExceptionDto(statusCode, errorMessage));
    }
}
