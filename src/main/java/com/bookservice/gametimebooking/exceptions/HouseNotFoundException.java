package com.bookservice.gametimebooking.exceptions;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
