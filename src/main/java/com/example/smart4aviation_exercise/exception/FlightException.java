package com.example.smart4aviation_exercise.exception;

import org.springframework.http.HttpStatus;

public class FlightException extends RuntimeException {

    private final HttpStatus httpStatus;

    public FlightException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
