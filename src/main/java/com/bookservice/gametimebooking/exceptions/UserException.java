package com.bookservice.gametimebooking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends RuntimeException{

    private HttpStatus status;

    public UserException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        setStatus(status);
    }
}
