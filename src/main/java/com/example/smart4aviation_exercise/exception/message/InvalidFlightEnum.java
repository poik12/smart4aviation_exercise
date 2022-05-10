package com.example.smart4aviation_exercise.exception.message;

public enum InvalidFlightEnum {

    FLIGHT_NOT_FOUND("Flight not found for requested flight number and date");

    public final String message;

    InvalidFlightEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
