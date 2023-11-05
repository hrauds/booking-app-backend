package com.bookservice.gametimebooking.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
