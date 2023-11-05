package com.bookservice.gametimebooking.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
