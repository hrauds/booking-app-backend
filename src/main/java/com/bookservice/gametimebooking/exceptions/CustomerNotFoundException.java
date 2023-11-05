package com.bookservice.gametimebooking.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
